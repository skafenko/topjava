package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

//    public static boolean isBetweenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
//        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
//    }
//
//    public static boolean isBetweenDate(LocalDate ld, LocalDate startDate, LocalDate endDate) {
//        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
//    }

    public static <T extends Comparable> boolean isBetween(T object, T start, T end) {
        return object.compareTo(start) >= 0 && object.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
