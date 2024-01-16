package de.h3xabyt3.ultimatetablist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
    public static String getTime(String Zone) {
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Zone));
        return simpleDateFormat.format(date);
    }

    public static String getSeconds(String Zone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        Date result = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Zone));
        return simpleDateFormat.format(result);
    }

    public static String getDate(String Zone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date result = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Zone));
        return simpleDateFormat.format(result);
    }

    public static String getWeekDay(String Zone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        Date result = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Zone));
        return simpleDateFormat.format(result);
    }

    public static String getMonth(String Zone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
        Date result = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Zone));
        return simpleDateFormat.format(result);
    }
}