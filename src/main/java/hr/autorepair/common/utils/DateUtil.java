package hr.autorepair.common.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtil {

    public static boolean isDateWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

}
