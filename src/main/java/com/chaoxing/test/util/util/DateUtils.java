package com.chaoxing.test.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateUtils
 * @description:
 * @author: wwb
 * @date: 2017-10-30 16:22:37
 * @version: ver 1.0
 */
public class DateUtils {

    public static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat YYYYMM = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final SimpleDateFormat HH = new SimpleDateFormat("HH");

    public static final SimpleDateFormat DEFAULT_FORMAT = YYYYMMDDHHMMSS;

    /** 将日期字符串根据默认的格式转换为日期
     * @Description:
     * @author: 王文彬
     * @version: 2016年4月21日 上午10:40:30
     * @param dateStr
     * @return Date
     */
    public static Date parse(String dateStr){
        return parse(dateStr, DEFAULT_FORMAT);
    }
    /** 将日期字符串根据指定的格式转换为日期
     * @Description:
     * @author: 王文彬
     * @version: 2015年12月3日 下午9:58:29
     * @param dateStr
     * @param sdf
     * @return Date
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf){
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 将日期根据给定的格式转换为字符串
     * @Description:
     * @author: 王文彬
     * @version: 2015年12月3日 下午10:00:27
     * @param date
     * @param sdf
     * @return String
     */
    public static String format(Date date, SimpleDateFormat sdf){
        return sdf.format(date);
    }
    /** 将日期根据默认的格式转换为字符串
     * @Description:
     * @author: 王文彬
     * @version: 2016年4月21日 上午10:42:07
     * @param date
     * @return String
     */
    public static String format(Date date){
        return format(date, DEFAULT_FORMAT);
    }
    /** 获取上下午
     * @Description: 
     * @author:
     * @Date: 2018-01-08 11:06:11
     * @param: date
     * @return: java.lang.String
     */
    public static String getMorningOrAfternoon(Date date) {
        int hour = Integer.valueOf(HH.format(date));
        return hour > 12 ? "下午" : "上午";
    }
}
