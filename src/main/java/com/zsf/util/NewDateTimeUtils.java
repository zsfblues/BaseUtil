package com.zsf.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created on 2017/11/7.
 * 使用 纯JDK8 时间包来编写的时间日期工具类
 * @author zhoushengfan
 *
 * @see java.time – 包含值对象的基础包
 * @see java.time.chrono – 提供对不同的日历系统的访问
 * @see java.time.format – 格式化和解析时间和日期
 * @see java.time.temporal – 包括底层框架和扩展特性
 * @see java.time.zone – 包含时区支持的类
 */
public final class NewDateTimeUtils {

    private NewDateTimeUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;

    /**
     * 将当前时间字符串按格式转换
     * @param timeStr
     * @param formatter
     * @return
     */
    public static LocalDateTime parseTime(String timeStr, DateTimeFormatter formatter) {
        if (formatter == null){
            return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
        }
        return LocalDateTime.parse(timeStr, formatter);
    }

    public static String parseTime(LocalDateTime time, TimeFormat format) {
        return format.formatter.format(time);
    }

    public static boolean isToday(int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        return date.equals(today);
    }

    /**
     * 根据用户输入的天数计算距离该天数后的时间戳
     * @param nextDays
     * @return timestamp = 当前时间(ms) + nextDays(间隔)
     */
    public static long timestampAfterDays(Integer nextDays){
        return Instant.now().plus(nextDays, ChronoUnit.DAYS).toEpochMilli();
    }

    public static String[] dateStringAfterMinute(LocalDateTime dateTime, long minute){
        String nowDateTime = null;
        String startTime = null;
        if (dateTime == null){
            startTime = TimeFormat.LONG_DATE_PATTERN_WITHOUT_EXTRA_SEPARATOR.formatter.format(LocalDateTime.now());
            nowDateTime = TimeFormat.LONG_DATE_PATTERN_WITHOUT_EXTRA_SEPARATOR.formatter.format(LocalDateTime.now().plusMinutes(minute));
        }else {
            startTime = TimeFormat.LONG_DATE_PATTERN_WITHOUT_EXTRA_SEPARATOR.formatter.format(dateTime);
            nowDateTime = TimeFormat.LONG_DATE_PATTERN_WITHOUT_EXTRA_SEPARATOR.formatter.format(dateTime.plusMinutes(minute));
        }
        return new String[]{startTime, nowDateTime};
    }

    /**
     *
     * @return 今天还有多少秒
     */
    public static long restSecOfToday(){
        LocalDateTime time = LocalDateTime.now();
        return 86400L - time.getLong(ChronoField.SECOND_OF_DAY);
    }

    public static long[] timestampRangeInToday(){
        LocalDate today = LocalDate.now();
        LocalDateTime atStartOfDay = today.atStartOfDay();
        ZoneOffset zoneOffset = OffsetDateTime.now(ZoneId.systemDefault ()).getOffset();
        return new long[]{atStartOfDay.toInstant(zoneOffset).toEpochMilli(),
                today.plusDays(1).atStartOfDay().toInstant(zoneOffset).toEpochMilli()};
    }

    public static int dayOfWeek(){
        return LocalDateTime.now().getDayOfWeek().getValue();
    }

    /**
     *
     * @param startDateStr 只包含年月日的日期字符串
     * @param endDateStr 只包含年月日的日期字符串
     * @return
     */
    public static int daysBetweenTwoDateStr(String startDateStr, String endDateStr) {
        return Period.between(LocalDate.parse(startDateStr), LocalDate.parse(endDateStr)).getDays();
    }

    private enum TimeFormat {
        /**
         * 短时间格式
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),
        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),
        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

        /**
         * 长时间格式
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),
        LONG_DATE_PATTERN_WITHOUT_EXTRA_SEPARATOR("yyyyMMddHHmmss"),

        /**
         * 长时间格式 带毫秒
         */
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");

        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }
}
