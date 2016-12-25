package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int BREAKFAST_30_05_15_ID = START_SEQ + 2;
    public static final int DINNER_30_05_15_ID = START_SEQ + 3;
    public static final int SUPPER_30_05_15_ID = START_SEQ + 4;
    public static final int BREAKFAST_31_05_15_ID = START_SEQ + 5;
    public static final int DINNER_31_05_15_ID = START_SEQ + 6;
    public static final int SUPPER_31_05_15_ID = START_SEQ + 7;
    public static final int ADMIN_LUNCH_1_06_15_ID = START_SEQ + 8;
    public static final int ADMIN_SUPPER_1_06_15_ID = START_SEQ + 9;

    public static final Meal BREAKFAST_30_05_15 = new Meal(BREAKFAST_30_05_15_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal DINNER_30_05_15 = new Meal(DINNER_30_05_15_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal SUPPER_30_05_15 = new Meal(SUPPER_30_05_15_ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal BREAKFAST_31_05_15 = new Meal(BREAKFAST_31_05_15_ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal DINNER_31_05_15 = new Meal(DINNER_31_05_15_ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal SUPPER_31_05_15 = new Meal(SUPPER_31_05_15_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_LUNCH_1_06_15 = new Meal(ADMIN_LUNCH_1_06_15_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_SUPPER_1_06_15 = new Meal(ADMIN_SUPPER_1_06_15_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

}
