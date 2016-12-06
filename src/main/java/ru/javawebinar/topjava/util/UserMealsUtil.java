package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }


    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesCounted =
                mealList.stream()
                        .collect(Collectors.toMap(u -> u.getDateTime().toLocalDate(), u -> u.getCalories(), (c1, c2) -> c1 + c2));

        return mealList
                .stream()
                .filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                .map(u -> new UserMealWithExceed(u.getDateTime(),
                        u.getDescription(),
                        u.getCalories(),
                        caloriesCounted.get(u.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

    }

//    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
//
//        Map<LocalDate, Integer> caloriesInDay = new HashMap<>();
//
//        for (UserMeal userMeal : mealList) {
//            LocalDate ld = userMeal.getDateTime().toLocalDate();
//            int calories = userMeal.getCalories();
//            if (caloriesInDay.containsKey(ld)) {
//                caloriesInDay.put(ld, caloriesInDay.get(ld) + calories);
//            } else {
//                caloriesInDay.put(ld, calories);
//            }
//        }
//        List<UserMealWithExceed> result = new ArrayList<>();
//        for (UserMeal userMeal : mealList) {
//            LocalDateTime dt = userMeal.getDateTime();
//            boolean exceed = caloriesInDay.get(dt.toLocalDate()) > caloriesPerDay;
//            if (TimeUtil.isBetween(dt.toLocalTime(), startTime, endTime)) {
//                result.add(new UserMealWithExceed(dt, userMeal.getDescription(), userMeal.getCalories(), exceed));
//            }
//        }
//
//        return result;
//    }

}
