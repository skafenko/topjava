package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mykhailo on 14.12.2016.
 */
public class MealDAOList implements MealDAO {
    private static List<Meal> meals = new CopyOnWriteArrayList<>();

    private static AtomicInteger counter = new AtomicInteger();

    static {
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        counter.set(meals.size());
    }

    @Override
    public void add(Meal meal) {
        if (meal != null) {
            meal.setId(counter.getAndIncrement());
            meals.add(meal);
        }
    }

    @Override
    public synchronized void delete(int id) {
        if (id >= 0 && id < counter.get())
            meals.remove(id);
    }

    @Override
    public synchronized void update(Meal meal) {
        if (meal != null) {
            Meal oldMeal = meals.get(meal.getId());
            oldMeal = meal;
        }
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public synchronized Meal getById(int id) {
        return meals.get(id);
    }
}
