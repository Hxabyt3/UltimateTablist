package de.h3xabyt3.ultimatetablist;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String getTime(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date result = new Date(ms);
        return simpleDateFormat.format(result);
    }

    public static String getSeconds(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        Date result = new Date(ms);
        return simpleDateFormat.format(result);
    }

    public static String getDate(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date result = new Date(ms);
        return simpleDateFormat.format(result);
    }

    public static String getWeekDay(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        Date result = new Date(ms);
        return simpleDateFormat.format(result);
    }

    public static String getMonth(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
        Date result = new Date(ms);
        return simpleDateFormat.format(result);
    }
}
