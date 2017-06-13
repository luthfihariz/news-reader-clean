package com.luthfihariz.newsreader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yosua on 11/23/16.
 */

public class CalendarUtil {
    private static final String TAG = "CalendarUtil";

    public static Calendar parseTime(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(date));
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatTime(Calendar calendar, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static String adjustTimePattern(String date, String oldPattern, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldPattern, Locale.getDefault());
        try {
            Date oldPatternDate = dateFormat.parse(date);
            dateFormat.applyPattern(pattern);
            return dateFormat.format(oldPatternDate);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}
