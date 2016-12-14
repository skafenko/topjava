package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Mykhailo on 14.12.2016.
 */
public interface MealDAO {

    void add(Meal meal);
    void delete(int id);
    void update(Meal meal);
    List<Meal> getAll();
    Meal getById(int id);
}
