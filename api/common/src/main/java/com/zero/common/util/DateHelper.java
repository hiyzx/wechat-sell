package com.zero.common.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description  时间的工具类
 * @author yezhaoxing
 * @date 2017/5/11
 */
public final class DateHelper {
    public DateHelper() {
    }

    public static Date strToDate(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToYYYYMMDD(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Timestamp strToTimestamp(String time) {
        Timestamp ts = null;

        try {
            SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = (Date) e.parseObject(time);
            ts = new Timestamp(d.getTime());
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return ts;
    }

    public static Date getCurrentDateTime() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getCurrentDateTime(String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String dateStr = dateFormat.format(new Date());
        return dateFormat.parse(dateStr);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date format(String source, String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(source);
    }

    public static int getYear(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static Date addYear(Date date, int years) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int months) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static Date addDay(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date addHour(Date date, int hours) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int minutes) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date addSecond(Date date, int seconds) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public static String dateDiff(String startTime, String endTime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 86400000L;
        long nh = 3600000L;
        long nm = 60000L;
        long day;
        long hour;
        long min;

        try {
            long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;
            hour = diff % nd / nh + day * 24L;
            min = diff % nd % nh / nm + day * 24L * 60L;
            return day < 1L ? (hour < 1L ? min + "分钟" : hour + "小时") : day + "天";
        } catch (ParseException var19) {
            var19.printStackTrace();
            return null;
        }
    }

    public static long getTime(String dateTime, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date = sdf.parse(dateTime);
            return date.getTime();
        } catch (ParseException var6) {
            var6.printStackTrace();
            return 0L;
        }
    }

    public static long getDiffDays(String time1, String time2) {
        long diffDays = 0L;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date e = ft.parse(time1);
            Date date2 = ft.parse(time2);
            diffDays = e.getTime() - date2.getTime();
            diffDays = diffDays / 1000L / 60L / 60L / 24L;
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

        return diffDays;
    }

    public static long getDiffMinute(String startTime, String endTime) {
        long diffMinute = 0L;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date e = ft.parse(endTime);
            Date dateStartTime = ft.parse(startTime);
            diffMinute = e.getTime() - dateStartTime.getTime();
            diffMinute = diffMinute / 1000L / 60L;
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

        return diffMinute;
    }

    public static boolean beforeDate(String lastDate, String nowDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date d = sdf.parse(lastDate);
        Date d2 = sdf.parse(nowDate);
        return d.before(d2);
    }

    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DATE);
    }

    public static List<Date> getDateList(Date dateStart, Date dateEnd) {
        ArrayList<Date> dateList = new ArrayList<>();
        dateList.add(dateStart);
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(dateStart);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dateEnd);

        while (dateEnd.after(calStart.getTime())) {
            calStart.add(Calendar.DATE, 1);
            dateList.add(calStart.getTime());
        }

        return dateList;
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        return isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }
}
