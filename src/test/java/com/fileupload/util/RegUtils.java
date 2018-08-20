package com.fileupload.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**正则表达式
 * @className: RegUtils
 * @description:
 * @author: wwb
 * @date: 2018-08-13 17:13:33
 * @version: ver 1.0
 */
public class RegUtils {

    /**手机
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-13 17:14:56
     * @param: value
     * @return: boolean
     */
    public static boolean mobile(String value) {
        String regExp = "^((13[0-9])|(15[0-3, 5-9])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(value);
        return m.matches();
    }

}
