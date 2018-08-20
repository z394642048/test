package com.fileupload.util;

import java.math.BigDecimal;

/**
 * @className: CalculateUtils
 * @description:
 * @author: wwb
 * @date: 2017-10-30 15:15:24
 * @version: ver 1.0
 */
public class CalculateUtils {

    // 默认除法运算精度
    private static final int DEFAULT_DIV_SCALE = 2;

    /** 提供精确的加法运算
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:25:05
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数的和
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    /** 提供精确的加法运算
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:25:26
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数数学加和
     */
    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    /** 提供精确的减法运算
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:25:45
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数的差
     */
    public static BigDecimal subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    /** 提供精确的减法运算
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:25:58
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数数学差
     */
    public static BigDecimal subtract(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /** 提供精确的乘法运算
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:26:08
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数的积
     */
    public static BigDecimal multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    /** 提供精确的乘法运算
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:26:22
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数的数学积
     */
    public static BigDecimal multiply(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /** 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:26:35
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数的商
     */
    public static BigDecimal divide(double v1, double v2) {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /** 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:26:48
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @return BigDecimal 两个参数的商
     */
    public static BigDecimal divide(double v1, double v2, int scale) {
        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /** 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:27:04
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return BigDecimal 两个参数的商
     */
    public static BigDecimal divide(double v1, double v2, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, round_mode);
    }

    /** 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:27:22
     * @param v1
     * @param v2
     * @return BigDecimal 两个参数的商
     */
    public static BigDecimal divide(String v1, String v2) {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /** 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:27:33
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @return String 两个参数的商
     */
    public static BigDecimal divide(String v1, String v2, int scale) {
        return divide(v1, v2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_EVEN);
    }

    /** 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:27:46
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return BigDecimal 两个参数的商
     */
    public static BigDecimal divide(String v1, String v2, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, round_mode);
    }

    /** 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:28:07
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return double 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /** 提供精确的小数位四舍五入处理
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:28:25
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return double 四舍五入后的结果
     */
    public static double round(double v, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, round_mode).doubleValue();
    }

    /** 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:28:43
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return String 四舍五入后的结果，以字符串格式返回
     */
    public static String round(String v, int scale) {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /** 提供精确的小数位四舍五入处理
     * @Description:
     * @author: 王文彬
     * @version: 2017年6月13日 下午2:29:00
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return String 四舍五入后的结果，以字符串格式返回
     */
    public static String round(String v, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, round_mode).toString();
    }

}
