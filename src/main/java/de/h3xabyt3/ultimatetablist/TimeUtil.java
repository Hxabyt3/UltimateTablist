package de.h3xabyt3.ultimatetablist;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    public static String getTime(String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date result = new Date(String.valueOf(TimeZone.getTimeZone(timezone)));
        return simpleDateFormat.format(result);
    }

    public static String getSeconds(String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        Date result = new Date(String.valueOf(TimeZone.getTimeZone(timezone)));
        return simpleDateFormat.format(result);
    }

    public static String getDate(String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date result = new Date(String.valueOf(TimeZone.getTimeZone(timezone)));

        return simpleDateFormat.format(result);
    }

    public static String getWeekDay(String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        Date result = new Date(String.valueOf(TimeZone.getTimeZone(timezone)));
        return simpleDateFormat.format(result);
    }

    public static String getMonth(String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
        Date result = new Date(String.valueOf(TimeZone.getTimeZone(timezone)));
        return simpleDateFormat.format(result);
    }
}
