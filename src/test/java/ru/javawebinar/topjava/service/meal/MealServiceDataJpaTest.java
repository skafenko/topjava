package ru.javawebinar.topjava.service.meal;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

/**
 * Created by Mykhailo on 16.01.2017.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATA_JPA})
public class MealServiceDataJpaTest extends MealServiceAbstTest {

    @Test
    public void testGetById() throws Exception {
        Meal actual = service.get(MealTestData.MEAL1_ID);
        Meal expected = MealTestData.MEAL1;
        expected.setUser(UserTestData.USER);
        MealTestData.MATCHER.assertEquals(actual, expected);
    }
}
