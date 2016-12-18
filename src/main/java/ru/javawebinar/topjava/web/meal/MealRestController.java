package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public class MealRestController {
    private MealService service;

    public Meal save(Meal meal) {
        return service.save(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) {
        return service.get(id, AuthorizedUser.id());
    }

    public Collection<Meal> getAll() {
        return service.getAll(AuthorizedUser.id());
    }

}
