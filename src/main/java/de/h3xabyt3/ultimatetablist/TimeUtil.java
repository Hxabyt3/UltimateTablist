package de.h3xabyt3.ultimatetablist;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;

public class TimeUtil {
    //Get time for specified timezone
    public static String getTime(String Zone) throws ZoneRulesException {
        return getTimeofTimeZone(ZoneId.of(Zone), "HH.mm").replace(".", ":");
    }
    //Get seconds for specified timezone
    public static String getSeconds(String Zone) throws ZoneRulesException {
        return getTimeofTimeZone(ZoneId.of(Zone), "ss");
    }
    //Get date for specified timezone
    public static String getDate(String Zone) throws ZoneRulesException {
        return getTimeofTimeZone(ZoneId.of(Zone), "dd.MM.yyyy");
    }
    //Get weekday for specified timezone
    public static String getWeekDay(String Zone) throws ZoneRulesException {
        return getTimeofTimeZone(ZoneId.of(Zone), "EEE");
    }
    //Get month for specified timezone
    public static String getMonth(String Zone) throws ZoneRulesException {
        return getTimeofTimeZone(ZoneId.of(Zone), "MMMM");
    }
    //Method returns a string containing the current time in the specified timezone and format
    private static String getTimeofTimeZone(ZoneId Zone, String format) {
        //Get time for timezone
        ZonedDateTime zonedDateTime = ZonedDateTime.now(Zone);
        //Add formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        //Return formatted version
        return zonedDateTime.format(formatter);
    }
}