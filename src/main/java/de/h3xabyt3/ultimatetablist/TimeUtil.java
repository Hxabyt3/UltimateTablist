package de.h3xabyt3.ultimatetablist;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String getTime(String Zone) {
        return getTimeofTimeZone(ZoneId.of(Zone), "HH.mm").replace(".", ":");
    }

    public static String getSeconds(String Zone) {
        return getTimeofTimeZone(ZoneId.of(Zone), "ss");
    }

    public static String getDate(String Zone) {
        return getTimeofTimeZone(ZoneId.of(Zone), "dd.MM.yyyy");
    }

    public static String getWeekDay(String Zone) {
        return getTimeofTimeZone(ZoneId.of(Zone), "EEE");
    }

    public static String getMonth(String Zone) {
        return getTimeofTimeZone(ZoneId.of(Zone), "MMMM");
    }

    private static String getTimeofTimeZone(ZoneId Zone, String format) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(Zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return zonedDateTime.format(formatter);
    }
}