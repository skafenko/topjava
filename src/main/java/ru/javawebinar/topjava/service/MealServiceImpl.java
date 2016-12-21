package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;


    @Override
    public Meal save(Meal meal, int userId) {
        Meal savedMeal = repository.save(meal, userId);
        if (savedMeal == null)
            throw new NotFoundException("Can't save " + meal + " with userId " + userId);

        return savedMeal;
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFound(repository.delete(id, userId), "id=" + id + ", userId=" + userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFound(repository.get(id, userId), "id=" + id + ", userId=" + userId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<Meal> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        return repository.getFiltered(startDate, startTime, endDate, endTime, userId);
    }
}
