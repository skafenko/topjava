package ru.javawebinar.topjava.util;

import java.time.LocalTime;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        if (startTime == null && endTime  == null) {
            return true;
        } else if (startTime != null && endTime == null) {
            return lt.compareTo(startTime) >= 0;
        } else if (startTime == null && endTime != null) {
            return lt.compareTo(endTime) <= 0;
        }
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
