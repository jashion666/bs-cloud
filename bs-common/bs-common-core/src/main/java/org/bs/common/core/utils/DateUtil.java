package org.bs.common.core.utils;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import org.bs.common.core.constant.DateConstant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 日期工具类
 *
 * @author :wkh.
 * @date :2020/5/25.
 */
public class DateUtil extends cn.hutool.core.date.DateUtil {

    /**
     * 字符串日期转换为时间默认方法（yyyyMMddHHmmss）
     *
     * @param strDate 字符串日期
     * @return 时间
     */
    public static LocalDateTime parseDate(String strDate) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_1));
    }

    /**
     * 字符串日期转换为时间
     *
     * @param strDate 字符串日期
     * @param format  转换格式
     * @return 时间
     */
    public static LocalDateTime parseDate(String strDate, String format) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 判断是否为指定的时间格式
     *
     * @param strDate 字符串日期
     * @param format  格式
     * @return boolean
     */
    public static boolean isDate(String strDate, String format) {

        try {
            parse(strDate, format);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * DateTime 转为字符串
     *
     * @param date DateTime
     * @return 字符串日期时间
     */
    public static String formatDateTime(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_1).format(date);
    }

    /**
     * DateTime 转为字符串
     *
     * @param date DateTime
     * @return 字符串日期时间
     */
    public static String formatDateTime(LocalDateTime date, String format) {
        return DateTimeFormatter.ofPattern(format).format(date);
    }

    /**
     * 取得当前时间的时间戳
     *
     * @return 字符串时间戳
     */
    public static String getMilli() {
        return String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    /**
     * 取得指定时间的时间戳
     *
     * @return 字符串时间戳
     */
    public static String getMilli(LocalDateTime dateTime) {
        return String.valueOf(dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    /**
     * 取得指定时间的时间戳
     *
     * @return 字符串时间戳
     */
    public static String getUnixMilli(LocalDateTime dateTime) {
        return String.valueOf((dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000L));
    }

    /**
     * 取得指定时间的时间戳(long 格式)
     *
     * @return 字符串时间戳
     */
    public static long getUnixMilliLong(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000L;
    }

    /**
     * 取得指定时间的时间戳(long 格式)
     *
     * @return 字符串时间戳
     */
    public static Integer getUnixMilliInt(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return Integer.parseInt(String.valueOf(dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000));
    }

    /**
     * 将时间戳转成LocalDateTime
     *
     * @param timeMillis unix 时间戳 10位
     * @return 格式化后的字符串
     */
    public static String formatUnixMilli(Long timeMillis) {
        if (timeMillis == null || timeMillis == 0) {
            return "";
        }
        LocalDateTime unixMilli = parseUnixMilli(timeMillis * 1000);
        String format = DateConstant.DATE_FORMAT_4;
        formatDateTime(unixMilli, format);
        return formatDateTime(unixMilli, format);
    }

    /**
     * 将时间戳转成格式化日期字符串
     *
     * @param timeMillis unix 时间戳 10位
     * @param format     格式化
     * @return 格式化后的字符串
     */
    public static String formatUnixMilli(Long timeMillis, String format) {
        if (timeMillis == null || timeMillis == 0) {
            return "";
        }
        LocalDateTime unixMilli = parseUnixMilli(timeMillis * 1000);
        formatDateTime(unixMilli, format);
        return formatDateTime(unixMilli, format);
    }

    /**
     * 将时间戳转成格式化日期字符串
     *
     * @param timeMillis unix 时间戳 10位
     * @param format     格式化
     * @return 格式化后的字符串
     */
    public static String formatUnixMilli(Integer timeMillis, String format) {
        if (timeMillis == null || timeMillis == 0) {
            return "";
        }
        return formatUnixMilli(Long.valueOf(timeMillis), format);
    }

    /**
     * 将时间戳转成LocalDateTime
     *
     * @return 字符串时间戳
     */
    public static LocalDateTime parseUnixMilli(Long timeMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
    }

    /**
     * 将时间戳转成LocalDateTime
     *
     * @return 字符串时间戳
     */
    public static LocalDateTime parseUnixMilli(Integer timeMillis) {
        if (timeMillis == null || timeMillis == 0) {
            return null;
        }
        long timeMillisLong = timeMillis * 1000L;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillisLong), ZoneId.systemDefault());
    }

    /**
     * 取得unix时间戳
     *
     * @return 字符串时间戳
     */
    public static Long getLongUnixMilli() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * 取得unix时间戳
     *
     * @return 字符串时间戳
     */
    public static int getIntUnixMilli() {
        long ret = System.currentTimeMillis() / 1000L;
        return (int) ret;
    }

    /**
     * 取得unix时间戳
     *
     * @return 字符串时间戳
     */
    public static int getUnixMilli() {
        return getLongUnixMilli().intValue();
    }

    /**
     * 将指定字符串日期转换为unix时间戳
     *
     * @return 字符串时间戳
     */
    public static Long parseLongUnixMilli(String strDate, String format) {
        LocalDateTime localDateTime = parseDate(strDate, format);
        return Long.valueOf(getUnixMilli(localDateTime));
    }

    /**
     * 将指定字符串日期转换为unix时间戳
     *
     * @return 字符串时间戳
     */
    public static Integer parseUnixMilli(String strDate, String format) {
        LocalDateTime localDateTime = parseDate(strDate, format);
        return Integer.valueOf(getUnixMilli(localDateTime));
    }

    /**
     * 取得最近几年的年份map
     *
     * @param limit 几年
     * @return 年份map
     */
    public static List<Integer> getRecentYears(int limit) {
        int year = thisYear();
        List<Integer> yearList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            yearList.add(year--);
        }
        return yearList;
    }

    /**
     * 取得本年度年初的unix时间戳
     *
     * @return 字符串时间戳
     */
    public static long getBeginOfYearUnixMilli() {
        return DateUtil.beginOfYear(new Date()).getTime() / 1000L;
    }

    /**
     * 取得本年度年末的unix时间戳
     *
     * @return 字符串时间戳
     */
    public static long getEndOfYearUnixMilli() {
        return DateUtil.endOfYear(new Date()).getTime() / 1000L;
    }

    /**
     * 取得指定度年初的unix时间戳
     *
     * @return 字符串时间戳
     */
    public static long getTargetBeginOfYearUnixMilli(int year) {
        DateTime time = DateUtil.parse(year + "-01-01");
        return DateUtil.beginOfYear(time).getTime() / 1000L;
    }

    /**
     * 取得指定度年末的unix时间戳
     *
     * @return 字符串时间戳
     */
    public static long getTargetEndOfYearUnixMilli(int year) {
        DateTime time = DateUtil.parse(year + "-01-01");
        return DateUtil.endOfYear(time).getTime() / 1000L;
    }

    /**
     * 取得查看历年推荐名单年份
     *
     * @return 年份
     */
    public static List<String> getRecommendedYears() {
        List<String> yearsList = new ArrayList<>();
        yearsList.add("2020");
        yearsList.add("2021");
        yearsList.add("2022");
        yearsList.add("2023");
        yearsList.add("2024");
        yearsList.add("2025");
        return yearsList;
    }

    /**
     * 取得查看历年推荐名单年份
     *
     * @return 年份
     */
    public static List<Integer> getYears(int limit, int begin) {
        List<Integer> yearsList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            yearsList.add(begin + i);
        }
        return yearsList;
    }

    /**
     * 取得指定范围的年度
     *
     * @param limit 范围 传入几就会生成几年
     * @return 年度map key 时间戳 value 年份
     */
    public static Map<Long, String> getYearPeriod(int limit) {
        int thisYear = thisYear();

        Map<Long, String> yearMap = new LinkedHashMap<>();
        for (int i = 0; i < limit; i++) {
            int key = thisYear + i;
            String start = key + "-03-01 00:00:00";
            String end = key + "-09-01 00:00:00";
            yearMap.put(DateUtil.parseLongUnixMilli(start, DateConstant.DATE_FORMAT_9), key + "上半年");
            yearMap.put(DateUtil.parseLongUnixMilli(end, DateConstant.DATE_FORMAT_9), key + "下半年");
        }
        return yearMap;
    }

    /**
     * 创建日期范围生成器
     *
     * @param startTime 开始时间戳
     * @param endTime   结束时间戳
     */
    public static List<Map<String, String>> rangeToList(Long startTime, Long endTime) {
        Date startDate = Date.from(DateUtil.parseUnixMilli(startTime * 1000).atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(DateUtil.parseUnixMilli(endTime * 1000).atZone(ZoneId.systemDefault()).toInstant());
        ArrayList<DateTime> dateTimes = CollUtil.newArrayList((Iterable<DateTime>) range(startDate, endDate, DateField.DAY_OF_YEAR));

        List<Map<String, String>> ret = new ArrayList<>();
        for (DateTime dateTime : dateTimes) {
            Map<String, String> map = new HashMap<>();
            map.put("value", String.valueOf(dateTime.getTime() / 1000));
            map.put("text", dateTime.toString(DateConstant.DATE_FORMAT_2));
            ret.add(map);
        }
        return ret;
    }
}
