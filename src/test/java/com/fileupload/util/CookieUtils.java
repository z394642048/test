package com.fileupload.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;

/**
 * @className: CookieUtils
 * @description:
 * @author: wwb
 * @date: 2017-10-30 16:21:55
 * @version: ver 1.0
 */
public class CookieUtils {

    /** 添加cookie
     * @Description:
     * @author: 王文彬
     * @version: 2016年10月10日 上午10:33:20
     * @param response
     * @param key
     * @param value
     * @return void
     */
    public static void addCookie(HttpServletResponse response, String key, String value){
        Cookie uidCookie = new Cookie(key, value);
        uidCookie.setPath("/");
        response.addCookie(uidCookie);
    }
    /**根据key获取cookie的值
     * @Description:
     * @author: wwb
     * @Date: 2017-09-30 10:56:47
     * @param: cookies
     * @param: key
     * @return: java.lang.String
     */
    public static String getValue(Cookie[] cookies, String key) {
        String value = null;
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if(key.equals(cookie.getName())){
                    value = cookie.getValue();
                    break;
                }
            }
        }
        return value;
    }
    /**
     * 解析封装cookie
     * @param cookieString
     * @return
     */
    public static Cookie parseCookie(String cookieString) {
        Cookie cookie ;
        StringTokenizer tokenizer = new StringTokenizer(cookieString, ";");
        String nameValuePair = tokenizer.nextToken();
        int index = nameValuePair.indexOf('=');
        if (index != -1) {
            String name = nameValuePair.substring(0, index).trim();
            String value = nameValuePair.substring(index + 1).trim();
            cookie = new Cookie(name, value);
        } else {
            throw new IllegalArgumentException("Invalid cookie name-value pair");
        }
        // remaining name-value pairs are cookie's attributes
        while (tokenizer.hasMoreTokens()) {
            nameValuePair = tokenizer.nextToken();
            index = nameValuePair.indexOf('=');
            String name, value;
            if (index != -1) {
                name = nameValuePair.substring(0, index).trim();
                value = nameValuePair.substring(index + 1).trim();
            } else {
                name = nameValuePair.trim();
                value = null;
            }
            switch (name) {
                case "Domain":
                    if (value.startsWith(".")) {
                        value = value.substring(1, value.length());
                    }
                    cookie.setDomain(value);
                    break;
                case "Path":
                    cookie.setPath(value);
                    break;
                case "HttpOnly":
                    cookie.setHttpOnly(true);
                    break;
                case "Expires":
//					cookie.setMaxAge(Integer.MAX_VALUE);
                    break;
                default:
                    // ignore...
            }
        }
        return cookie;
    }

}
