package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Mykhailo on 25.12.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    @Autowired
    MealService service;

    @Autowired
    DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }


    @Test
    public void testSave() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "ужин", 500);
        Meal created = service.save(meal, USER_ID);
        meal.setId(created.getId());
        Meal meal2 = service.get(meal.getId(), USER_ID);
        MATCHER.assertEquals(meal, meal2);
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(BREAKFAST_30_05_15_ID, USER_ID);
        MATCHER.assertEquals(meal, BREAKFAST_30_05_15);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() throws Exception {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundWithWrongUserGet() throws Exception {
        service.get(ADMIN_SUPPER_1_06_15_ID, USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(DINNER_30_05_15_ID, USER_ID);
        service.delete(BREAKFAST_30_05_15_ID, USER_ID);
        service.delete(SUPPER_30_05_15_ID, USER_ID);
        service.delete(BREAKFAST_31_05_15_ID, USER_ID);
        service.delete(DINNER_31_05_15_ID, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(SUPPER_31_05_15), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundWithWrongUserDelete() throws Exception {
        service.delete(BREAKFAST_30_05_15_ID, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<Meal> meals = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 31), DateTimeUtil.MAX_DATE, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(SUPPER_31_05_15, DINNER_31_05_15, BREAKFAST_31_05_15), meals);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.JUNE, 1, 10, 0),
                LocalDateTime.of(2015, Month.JUNE, 1, 15, 0), ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_LUNCH_1_06_15), meals);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> meals = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(SUPPER_31_05_15, DINNER_31_05_15, BREAKFAST_31_05_15,
                SUPPER_30_05_15, DINNER_30_05_15, BREAKFAST_30_05_15), meals);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(ADMIN_LUNCH_1_06_15_ID, LocalDateTime.now(), "new Lunch", 100);
        Meal updated = service.update(meal, ADMIN_ID);
        MATCHER.assertEquals(meal, updated);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundWithWrongUserUpdate() throws Exception {
        Meal meal = new Meal(ADMIN_SUPPER_1_06_15_ID, LocalDateTime.now(), "dinner", 1000);
        Meal updated = service.update(meal, USER_ID);
        MATCHER.assertEquals(meal, updated);
    }

}