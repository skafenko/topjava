package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);
        if (meal.isNew()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String insertSQL = "INSERT INTO meals (user_id, date_time, description, calories) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSQL, new String[]{"id"});
                ps.setInt(1, userId);
                ps.setTimestamp(2, Timestamp.valueOf(meal.getDateTime()));
                ps.setString(3, meal.getDescription());
                ps.setInt(4, meal.getCalories());
                return ps;
            }, keyHolder);
            meal.setId(keyHolder.getKey().intValue());
        } else {
            int affectedRows = jdbcTemplate.update("UPDATE meals SET date_time=?, description=?, calories=? WHERE id=? AND user_id=?",
                    meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.getId(), userId);
            if (affectedRows == 0)
                return null;
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? AND date_time>=? AND date_time<=? " +
                "ORDER BY date_time DESC", ROW_MAPPER, userId, startDate, endDate);

    }
}
