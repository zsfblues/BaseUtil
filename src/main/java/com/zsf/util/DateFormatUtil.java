package com.zsf.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 处理日期的格式和样式转换
 */
public final class DateFormatUtil {

    private DateFormatUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String DATE_FORMAT_1 = "yyyy-MM-dd";
    public static String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";


    /**
     * 处理回传的日期中的空格
     *
     * @param str
     * @return
     */
    public static String replaceAll(String str) {
        try {
            return str.replaceAll(" ", "&nbsp;");
        } catch (NullPointerException e) {
            return "";
        }

    }

    /**
     * 获取字符串对应的日期
     *
     * @param str
     * @return
     */
    public static Date getString(String str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将时间字符串转换为long类型
     *
     * @param str
     * @return
     */
    public static long parse(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (str == null || str.equals("")) {
                return 0;
            }
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * 将时间字符串精确到分转换为long类型
     *
     * @param str
     * @return
     */
    public static long parseMinute(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    //将long类型的时间转换为需要的格式的时间字符串
    public static String parseString(long str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(str);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    //将long类型的时间转换为需要的格式的Date类型
    public static Date parseDate(long str) {
        Date date = new Date();
        date.setTime(str);
        return date;
    }

    //将long类型的时间转换为需要的格式的时间字符串
    public static String parseMinuteString(long str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        date.setTime(str);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    //将long类型的时间转换为需要的格式的时间字符串
    public static String parseDateString(long str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setTime(str);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    //获取当天零时的时间毫秒数
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    //获取当天23时59分59秒的时间毫秒数
    public static long getDttStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTimeInMillis();
    }

    public static Date getLastTimeDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }


    //获取传进来的当天23时59分59秒的时间毫秒数
    public static long getDtStart(Long dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dt));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTimeInMillis();
    }

    //获取传进来的当天零时的时间毫秒数
    public static long getDtEnd(Long dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dt));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    //将String类型转为long类型，格式：yyyy-MM-dd
    public static long parseFomt(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    //将String类型转为long类型，格式：yyyy-MM-dd
    public static long parseLongFormat(String str, long defaul) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException | NullPointerException e) {
            return defaul;
        }
    }

    /**
     * 获取与指定日期相差 day 天的日期 day = -1 表示指定日期的前一天，day = 1 表示指定日期的后一天
     * 参数1：指定日期 nowDate
     * 参数2：天数 day
     * 返回格式： yyyy-MM-dd
     */
    public static String getDay(Date nowDate, int day) {
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(nowDate);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, day);

        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String dateTime = sdf.format(date);
        return dateTime;
    }

    /**
     * 获取与指定日期相差 day 天的日期 day = -1 表示指定日期的前一天，day = 1 表示指定日期的后一天
     * 参数1：指定日期 String str
     * 参数2：天数 day
     * 返回格式： yyyy-MM-dd
     */
    public static String getStringDay(String str, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(sdf.parse(str));//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, day);
            String dateTime = sdf.format(calendar.getTime());
            return dateTime;
        } catch (Exception e) {

        }
        return "";
    }


    /**
     * 获取与指定long类型日期相差 day 天的日期 day = -1 表示指定日期的前一天，day = 1 表示指定日期的后一天
     * 参数1：指定日期 long str
     * 参数2：天数 day
     * 返回格式： yyyy-MM-dd
     */
    public static long getLongDay(long str, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(new Date(str));//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, day);
            String dateTime = sdf.format(calendar.getTime());
            Date date = sdf.parse(dateTime);
            return date.getTime();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * 获取与指定long类型日期相差 day 天的日期 day = -1 表示指定日期的前一天，day = 1 表示指定日期的后一天
     * 参数1：指定日期 long str
     * 参数2：天数 day
     * 返回格式： yyyy-MM-dd HH:mm:ss
     */
    public static long getLongDay2(long str, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(new Date(str));//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, day);
            String dateTime = sdf.format(calendar.getTime());
            Date date = sdf.parse(dateTime);
            return date.getTime();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * 比较两个时间的差的天数，不算时分秒 格式 ： yyyy-MM-dd
     * 返回日期相差的天数
     */
    public static int dayToDay(String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long date = end.getTime() - start.getTime();
            return (int) (date / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * 比较两个long类型（毫秒）时间相差的时间，这里按24小时计算，满24小时为一天
     * int days = endDate - startDate;
     * 注意先后顺序
     *
     * @param startDate
     * @param endDate
     * @return days 相差天数
     */
    public static int getBetweenDay(long startDate, long endDate) {
        Calendar d1 = new GregorianCalendar();
        d1.setTime(new Date(startDate));
        Calendar d2 = new GregorianCalendar();
        d2.setTime(new Date(endDate));
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * Date 类型转成String类型
     * 返回的格式：yyyy-MM-dd
     * 参数：传入时间Date
     */
    public static String getString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String getString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * String 类型转成long类型
     * 返回的格式：yyyy-MM-dd
     * 参数：传入时间str
     */
    public static long getStrlong(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * 根据String类型的时间获取这一日期是星期几
     * 返回参数：1-周一，2-周二，3-周三，4-周四，5-周五，5-周六，7-周日
     * 传入参数：时间str
     */
    public static int getWeek(String str) {
        int dayForWeek = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(str));
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return dayForWeek;
    }

    /**
     * 根据String类型的时间获取这一日期是星期几
     * 返回参数：1-周一，2-周二，3-周三，4-周四，5-周五，5-周六，7-周日
     * 传入参数：时间str
     */
    public static int getWeekByLong(Long str) {
        int dayForWeek = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new Date(str));
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dayForWeek;
    }

    /**
     * 获取当前时间
     * 参数：day day = 1 表示当前日期的下一天， day = -1 表示当前日期的前一天
     * 返回：yyyy-MM-dd
     */
    public static String getToDay(int day) {
        Calendar calendar = Calendar.getInstance();//获取日记
        calendar.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = calendar.getTime();
        String today = format.format(date);
        return today;
    }

    /**
     * 获取当前时间的long类型
     * 参数：day day = 1 表示当前日期的下一天， day = -1 表示当前日期的前一天， = 0 表示当前日期
     * 返回：yyyy-MM-dd
     */
    public static long getToDayLong(int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();//获取日记
        calendar.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(format.format(calendar.getTime()));
        return date.getTime();
    }

    /**
     * 获取当前时间的long类型
     * 参数：day day = 1 表示当前日期的下一天， day = -1 表示当前日期的前一天， = 0 表示当前日期
     * 返回：yyyy-MM-dd HH:mm:ss
     */
    public static long getToDayLongSs(int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();//获取日记
        calendar.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(format.format(calendar.getTime()));
        return date.getTime();
    }

    /**
     * 获取当天格式为 yyyy-MM-dd的日期
     */
    public static String getTodayString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获取当天格式为 yyyy-MM-dd-HH:mm:ss的日期
     */
    public static String getTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 将时间 yyyy-MM-dd 转为 yyyy年MM月dd日
     */
    public static String transform(String str) throws ParseException {
        SimpleDateFormat format_str = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format_str.parse(str);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String time = format.format(date);
        return time;
    }

    /**
     * 获得一年中的第一天
     * parm day = 0 表示今年的第一天，day = 1 下一年的第一天
     */
    public static long getFirstDay(int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) + day;
        String str = "" + year + "-1-1";
        SimpleDateFormat format_str = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format_str.parse(str);
        return date.getTime();
    }

    /**
     * 将yyyy-MM-dd 格式的时间年份替换成本年
     * parm day = 0 表示今年的第一天，day = 1 下一年的第一天
     */
    public static long setReplaceYear(long birthday) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String birthdayStr = parseDateString(birthday);
        String str[] = birthdayStr.split("-");
        String birthday_ = year + "-" + str[1] + "-" + str[2];
        SimpleDateFormat format_str = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format_str.parse(birthday_);
        return date.getTime();
    }

    /**
     * 获取指定时间对应的毫秒数
     *
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            SimpleDateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 产生12位随机数
     */
    public static String getRandom(int id) {
        int len = 0;
        int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
                99999999, 999999999, Integer.MAX_VALUE};
        for (int i = 0; i < sizeTable.length; i++) {
            if (id <= sizeTable[i]) {
                len = i + 1;
                break;

            }
        }
        String randomStr = "";
        for (int i = 0; i < 12 - len; i++) {
            int yy = (int) ((Math.random()) * 10);
            randomStr += String.valueOf(yy);
        }
        randomStr += String.valueOf(id);
        return randomStr;
    }

    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTimeInMillis();
    }

    /**
     * 判断两个时间是不是同一天
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isSameDayOfMillis(long start, long end) {
        boolean flg = false;
        long MILLIS_IN_DAY = 60 * 60 * 24 * 1000l;//一天的毫秒数
        long interval = end - start;
        long startTime = (start + TimeZone.getDefault().getOffset(start)) / MILLIS_IN_DAY;//获取偏移量
        long endTime = (end + TimeZone.getDefault().getOffset(end)) / MILLIS_IN_DAY;
        if (interval < MILLIS_IN_DAY && startTime == endTime) {
            flg = true;
        }
        return flg;
    }

    //获取当月第一天
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天并且时间是23:59:59.0
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //获取下个月第一天
    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取传进去日期的当月最小日期
     * 传入2017-10-15
     * 返回 2017-10-01
     *
     * @param time yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getFirstDayOfMonthByStr(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(time);
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期

        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取传进去日期的当月的最大日期
     * 传入2017-10-15
     * 返回 2017-10-31
     *
     * @param time yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getLastDayOfMonthByStrByLong(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(time);
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 获取传进去日期的当月最小日期
     * 传入2017-10-15
     * 返回 2017-10-01
     *
     * @param time yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getFirstDayOfMonthByLong(long time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(new Date(time));
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期

        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取传进去日期的当月的最大日期
     * 传入2017-10-15
     * 返回 2017-10-31
     *
     * @param time yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getLastDayOfMonthByStr(long time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(new Date(time));
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }


    /**
     * 返回今天剩余的秒数
     *
     * @return
     */
    public static int getNowSeconds() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        int second = DateFormatUtil.subSecond(cal.getTime(), new Date());
        return second;
    }

    /**
     * 返回date1-dat2相差的秒数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int subSecond(Date date1, Date date2) {
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        int sub = (int) ((d1 - d2) / 1000);
        return sub;
    }

    /**
     * 当前日期
     */
    public static int getDayID() {
        Calendar nowDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        int date_id = Integer.parseInt(sdf.format(nowDate.getTime()));
        return date_id;
    }


    /**
     * 增加天数后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateByDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date dateResult = calendar.getTime();
        return dateResult;
    }

    /**
     * 获取传进来的当天12时00分00秒的时间
     *
     * @param dt
     * @return
     */
    public static Date getDt(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return cal.getTime();
    }

    /**
     * 返回年,月和日
     *
     * @param dt
     * @return
     */
    public static Calendar getDtForZero(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 获取当月天数
     *
     * @param date
     * @return
     */
    public static int getDaysByDate(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, 1);
        now.roll(Calendar.DATE, -1);
        int maxDate = now.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取当月天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 在基础时间加上day(天数)
     *
     * @param baseCalendar
     * @param day          天数
     * @return
     */
    public static Date getNewDate(Calendar baseCalendar, int day) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(Calendar.YEAR, baseCalendar.get(Calendar.YEAR));
        newCalendar.set(Calendar.MONTH, baseCalendar.get(Calendar.MONTH));
        newCalendar.set(Calendar.DATE, baseCalendar.get(Calendar.DATE));
        newCalendar.set(Calendar.HOUR_OF_DAY, 0);
        newCalendar.set(Calendar.MINUTE, 0);
        newCalendar.set(Calendar.SECOND, 0);
        newCalendar.set(Calendar.MILLISECOND, 0);

        newCalendar.add(Calendar.DAY_OF_YEAR, day);
        return newCalendar.getTime();
    }

    /**
     * 添加天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static Date getTodayZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getToday23() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 根据分获取新的时间
     *
     * @param date
     * @param offsetMinute
     * @return
     */
    public static Date getDateByMinute(Date date, int offsetMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, offsetMinute);
        return calendar.getTime();
    }

    /**
     * 返回两个时间间的所有日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> getBetweenEveryDate(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return Collections.EMPTY_LIST;
        }

        Calendar beginCalendar = getDtForZero(beginDate);
        Calendar endCalendar = getDtForZero(endDate);
        if (beginCalendar.after(endCalendar)) {
            return Collections.EMPTY_LIST;
        }

        long beginTime = beginCalendar.getTimeInMillis();
        long endTime = endCalendar.getTimeInMillis();

        long intervalForDay = (endTime - beginTime) / (1000 * 60 * 60 * 24);

        // 不能超过500天
        if (intervalForDay > 500) {
            return Collections.EMPTY_LIST;
        }
        List<Date> dateList = new ArrayList();
        for (long i = 0; i < intervalForDay; i++) {
            dateList.add(getNewDate(beginCalendar, (int) i));
        }
        return dateList;
    }

    /**
     * 获取两个日期间天数
     * @param beginDt
     * @param endDt
     * @return
     */
    public static int getDays(Date beginDt, Date endDt) {
        return (int) ((endDt.getTime() - beginDt.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取当前是几月
     * @param date
     * @return
     */
    public static int getMouth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.MONTH)+1;
    }

    /**
     * 获得一年中的第一天
     */
    public static Date getFirstDayOfYear(int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) + day;
        String str = "" + year + "-1-1";
        SimpleDateFormat format_str = new SimpleDateFormat("yyyy-MM-dd");
        return format_str.parse(str);
    }

    /**
     * 获得今天之前每个月第一天
     */
    public static List<Date> getFirstDayOfMouths() throws ParseException {
        List<Date> list = new ArrayList<>();

        int mouths = getMouth(new Date());
        Date date = getFirstDayOfYear(0);
        for(int i=0;i<mouths;i++) {
            list.add(date);
            date = getFirstDayOfNextMonth(date);
        }

        return list;
    }

    /**
     * 计算时间之前的天数。
     * 忽略时分秒
     * @param start
     * @param end
     * @return
     */
    public static int betweenDays(Date start, Date end) {
        Calendar startInstance = Calendar.getInstance();
        startInstance.setTime(start);
        startInstance.set(Calendar.HOUR_OF_DAY, 0);
        startInstance.set(Calendar.MINUTE, 0);
        startInstance.set(Calendar.SECOND, 0);
        startInstance.set(Calendar.MILLISECOND, 0);

        Calendar endInstance = Calendar.getInstance();
        endInstance.setTime(end);
        endInstance.set(Calendar.HOUR_OF_DAY, 0);
        endInstance.set(Calendar.MINUTE, 0);
        endInstance.set(Calendar.SECOND, 0);
        endInstance.set(Calendar.MILLISECOND, 0);

        long intervalMill = endInstance.getTime().getTime() - startInstance.getTime().getTime();

        long days = intervalMill / 1000 / 60 / 60 / 24;

        return (int) days;
    }

}
