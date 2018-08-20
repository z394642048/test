package com.chaoxing.test.util.util;//package com.fileupload.util;
//
//
//import com.chaoxing.basicedu.readactivity.platform.constant.HttpRequestHeaderConstant;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @className: HttpServletRequestUtils
// * @description:
// * @author: wwb
// * @date: 2017-10-30 15:09:13
// * @version: ver 1.0
// */
//public class HttpServletRequestUtils {
//
//    /** 获取用户代理
//     * @Description:
//     * @author: 王文彬
//     * @version: 2017年1月10日 上午11:13:43
//     * @param request
//     * @return String
//     */
//    public static String getUserAgent(HttpServletRequest request){
//        String userAgent = request.getHeader(HttpRequestHeaderConstant.USER_AGENT);
//        return userAgent;
//    }
//    /** 获取ip
//     * @Description:
//     * @author: 王文彬
//     * @version: 2016年10月19日 上午10:39:12
//     * @param request
//     * @return String
//     */
//    public static String getIP(HttpServletRequest request){
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//    /** 获取访问的根地址
//     * @Description:
//     * @author:
//     * @Date: 2018-03-09 17:25:12
//     * @param: request
//     * @return: java.lang.String
//     */
//    public static String getRootUrl(HttpServletRequest request) {
//        String requestURL = request.getRequestURL().toString();
//        String requestURI = request.getRequestURI();
//        int index = requestURL.indexOf(requestURI);
//        if (StringUtils.isEmpty(requestURI)) {
//            index = requestURL.length();
//        } else if ("/".equals(requestURI)) {
//            index = requestURL.length() - 1;
//        }
//        return requestURL.substring(0, index);
//    }
//    /**获取域名
//     * @Description:
//     * @author: wwb
//     * @Date: 2018-08-10 11:33:02
//     * @param: request
//     * @param: domainLevel
//     * @return: java.lang.String
//     */
//    public static String getDomain(HttpServletRequest request, UrlUtils.DomainLevel domainLevel) {
//        StringBuffer url = request.getRequestURL();
//        String requestUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
//        return UrlUtils.getDomain(requestUrl, domainLevel);
//    }
//    /**获取请求url(不包含域名)
//     * @Description:
//     * @author: wwb
//     * @Date: 2018-08-10 14:05:01
//     * @param: request
//     * @return: java.lang.String
//     */
//    public static String getRequestUrl(HttpServletRequest request) {
//        StringBuffer url = request.getRequestURL();
//        String requestUrl = url.delete(0, url.length() - request.getRequestURI().length()).toString();
//        return requestUrl;
//    }
//    /**获取域名
//     * @Description:
//     * @author: wwb
//     * @Date: 2018-08-10 15:26:53
//     * @param: request
//     * @return: java.lang.String
//     */
//    public static String getDomain(HttpServletRequest request) {
//        StringBuffer url = request.getRequestURL();
//        String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
//        return domain;
//    }
//    /**是不是get请求
//     * @Description:
//     * @author: wwb
//     * @Date: 2018-08-10 14:11:00
//     * @param: request
//     * @return: boolean
//     */
//    public static boolean isGet(HttpServletRequest request) {
//        return "get".equalsIgnoreCase(request.getMethod());
//    }
//}
