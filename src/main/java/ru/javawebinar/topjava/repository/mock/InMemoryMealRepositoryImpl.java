package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(m, m.getUser().getId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getUser().getId() != userId) {
            return null;
        }

        String save = meal.isNew() ? "save " : "update ";
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        LOG.info(save + meal);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete " + id);
        if (get(id, userId) != null) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        LOG.info("get " + id);
        Meal meal = repository.get(id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        LOG.info("getAll with userId " + userId);
        return repository.values().stream()
                .filter(m -> m.getUser().getId() == userId)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

