package com.fileupload.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: UrlUtils
 * @description:
 * @author: wwb
 * @date: 2018-08-10 11:30:01
 * @version: ver 1.0
 */
public class UrlUtils {

    private static final String RE_TOP_DOMAIN = "(com\\.cn|net\\.cn|gov\\.cn|org\\.nz|org\\.cn|com|net|org|gov|cc|biz|info|cn|co|me)";

    // 一级域名提取
    private static final String RE_TOP_1 = "(\\w*\\.?){1}\\." + RE_TOP_DOMAIN;

    // 二级域名提取
    private static final String RE_TOP_2 = "(\\w*\\.?){2}\\." + RE_TOP_DOMAIN;

    // 三级域名提取
    private static final String RE_TOP_3 = "(\\w*\\.?){3}\\." + RE_TOP_DOMAIN;

    private static final Pattern PATTEN_IP = Pattern.compile("((http://)|(https://))?((\\d+\\.){3}(\\d+))");
    private static final Pattern PATTEN_TOP1 = Pattern.compile(RE_TOP_1);
    private static final Pattern PATTEN_TOP2 = Pattern.compile(RE_TOP_2);
    private static final Pattern PATTEN_TOP3 = Pattern.compile(RE_TOP_3);

    public static enum  DomainLevel{
        FIRT(1),SECOND(2),THIRD(3);

        private int value;

        DomainLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**获取域名
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-10 11:30:21
     * @param: url
     * @param: domainLevel
     * @return: java.lang.String
     */
    public static String getDomain(String url, DomainLevel domainLevel) {
        Matcher matcher = PATTEN_IP.matcher(url);
        if (matcher.find()){
            return matcher.group(4);
        }
        matcher = handleMatcher(matcher, url, domainLevel);
        if (matcher.find()) {
            String group = matcher.group(0);
            String[] split = group.split("\\.");
            if (split.length > domainLevel.getValue()) {
                return split[0];
            }
            return "";
        }
        return "";
    }
    public static String getFullDomain(String url, DomainLevel domainLevel) {
        Matcher matcher = PATTEN_IP.matcher(url);
        if (matcher.find()){
            return matcher.group(4);
        }
        matcher = handleMatcher(matcher, url, domainLevel);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    private static Matcher handleMatcher(Matcher matcher, String url, DomainLevel domainLevel) {
        switch (domainLevel) {
            case FIRT:
                matcher = PATTEN_TOP1.matcher(url);
                break;
            case SECOND:
                matcher = PATTEN_TOP2.matcher(url);
                break;
            case THIRD:
                matcher = PATTEN_TOP3.matcher(url);
                break;
        }
        return matcher;
    }

}
