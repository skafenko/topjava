package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal extends BaseEntity {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private Integer userId;

    public Meal(int userId, LocalDateTime dateTime, String description, int calories) {
        this(userId, null, dateTime, description, calories);
    }

    public Meal(int userId, Integer id, LocalDateTime dateTime, String description, int calories) {
        this.userId = userId;
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", userId=" + userId +
                '}';
    }
}
