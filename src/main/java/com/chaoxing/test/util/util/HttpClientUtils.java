package com.chaoxing.test.util.util;

import okhttp3.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: HttpClientUtils
 * @description:
 * @author: wwb
 * @date: 2017-10-30 15:09:41
 * @version: ver 1.0
 */
public class HttpClientUtils {

    private OkHttpClient okHttpClient;

    private static HttpClientUtils getInstance() {
        return InnerClass.httpUtils;
    }

    private HttpClientUtils() {
        okHttpClient = new OkHttpClient().newBuilder().connectTimeout(60 * 10, TimeUnit.SECONDS).build();
    }

    private static class InnerClass {
        private static final HttpClientUtils httpUtils = new HttpClientUtils();
    }

    private static OkHttpClient getOkHttpClient(boolean proxy) {
        /*String active = PropertiesUtils.getStringValue("spring.profiles.active");
        if ("dev".equals(active) && proxy) {
            return getInstance().okHttpClient.newBuilder().proxy(getProxy()).build();
        }*/
        if (proxy) {
            return getInstance().okHttpClient.newBuilder().proxy(getProxy()).build();
        }
        return getInstance().okHttpClient;
    }

    /**获取代理
     * @Description: 
     * @author: wwb
     * @Date: 2018-06-06 09:40:12
     * @param: 
     * @return: java.net.Proxy
     */
    private static Proxy getProxy() {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 3099));
    }

    /** 结果bean
     * @Description: 
     * @author: 
     * @Date: 2017-11-30 18:45:29
     * @param: null
     * @return: 
     */
    public static class ResultBean {

        private Response response;
        private String result;

        public Response getResponse() {
            return response;
        }

        public void setResponse(Response response) {
            this.response = response;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    /**
     * 获取字符串值
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-26 16:35:28
     * @param: response
     * @return: java.lang.String
     */
    private static String getStringValue(Response response) {
        String result = null;
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            try {
                result = responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**get请求
     * @Description:
     * @author: wwb
     * @Date: 2018-08-06 09:24:39
     * @param: url 请求的url
     * @param: proxy 是否使用代理
     * @param: cookie 需要携带的cookie信息，无则传null
     * @return: com.chaoxing.basicedu.readactivity.platform.util.HttpClientUtils.ResultBean
     */
    public static ResultBean get(String url, boolean proxy, String cookie) throws IOException {
        ResultBean resultBean = new ResultBean();
        String result;
        Request.Builder builder = new Request.Builder();
        if (!StringUtils.isEmpty(cookie)) {
//            builder.addHeader(HttpRequestHeaderConstant.COOKIE, cookie);
        }
        Request request = builder.get().url(url).build();
        Response response = getOkHttpClient(proxy).newCall(request).execute();
        if (response.isSuccessful()) {
            result = getStringValue(response);
        } else {
            result = response.message();
        }
        resultBean.setResult(result);
        resultBean.setResponse(response);
        return resultBean;
    }

    /**
     * get请求
     * @Description:
     * @author: 王文彬
     * @Date: 2017-07-07 00:21:59
     * @param: url
     * @param: headers
     * @return: com.chaoxing.basicedu.wzlibrary.util.HttpClientUtils.ResultBean
     */
    public static ResultBean get(String url, Map<String, String> headers, boolean proxy, String cookie) throws IOException {
        ResultBean resultBean = new ResultBean();
        String result;
        Request.Builder builder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            headers.entrySet().forEach(entry -> {
                builder.addHeader(entry.getKey(), entry.getValue());
            });
        }
        if (!StringUtils.isEmpty(cookie)) {
//            builder.addHeader(HttpRequestHeaderConstant.COOKIE, cookie);
        }
        Request request = builder.get().url(url).build();
        Response response = getOkHttpClient(proxy).newCall(request).execute();
        if (response.isSuccessful()) {
            result = getStringValue(response);
        } else {
            result = response.message();
        }
        resultBean.setResult(result);
        resultBean.setResponse(response);
        return resultBean;
    }

    /**
     * post请求
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-27 14:17:44
     * @param: url
     * @param: params
     * @return: com.chaoxing.basicedu.wzlibrary.util.HttpClientUtils.ResultBean
     */
    public static ResultBean post(String url, Map<String, Object> params, boolean proxy, String cookie) throws IOException {
        return post(url, params, null, proxy, cookie, null);
    }
    /**post请求
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-13 15:00:21
     * @param: url
     * @param: params
     * @param: proxy
     * @param: cookie
     * @param: response
     * @return: com.chaoxing.basicedu.readactivity.platform.util.HttpClientUtils.ResultBean
     */
    public static ResultBean post(String url, Map<String, Object> params, boolean proxy, String cookie, HttpServletResponse response) throws IOException {
        return post(url, params, null, proxy, cookie, response);
    }

    /**post请求
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-13 14:58:35
     * @param: url 请求地址
     * @param: params 参数
     * @param: headers 添加的头部
     * @param: proxy 是否使用代理
     * @param: cookie 携带的cookie
     * @param: httpServletResponse 不为空则添加cookie
     * @return: com.chaoxing.basicedu.readactivity.platform.util.HttpClientUtils.ResultBean
     */
    public static ResultBean post(String url, Map<String, Object> params, Map<String, String> headers, boolean proxy, String cookie, HttpServletResponse httpServletResponse) throws IOException {
        ResultBean resultBean = new ResultBean();
        String result;
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            params.entrySet().forEach(entry -> {
                builder.add(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
            });
        }
        FormBody formBody = builder.build();
        Request.Builder requestBuilder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            headers.entrySet().forEach(entry -> {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            });
        }
        if (!StringUtils.isEmpty(cookie)) {
//            requestBuilder.addHeader(HttpRequestHeaderConstant.COOKIE, cookie);
        }
        Request request = requestBuilder.post(formBody).url(url).build();
        OkHttpClient client = getOkHttpClient(proxy).newBuilder().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            result = getStringValue(response);
            if (httpServletResponse != null) {//添加cookie
                response.headers("Set-Cookie").forEach(header -> {
                    httpServletResponse.addCookie(CookieUtils.parseCookie(header));
                });
            }
        } else {
            result = response.message();
        }
        resultBean.setResult(result);
        resultBean.setResponse(response);
        return resultBean;
    }

    /**上传文件
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-03 12:38:32
     * @param: url
     * @param: params
     * @param: proxy
     * @param: fileMap
     * @return: com.chaoxing.basicedu.readactivity.platform.util.HttpClientUtils.ResultBean
     */
    public static ResultBean upload(String url, Map<String, Object> params, boolean proxy, Map<String, File> fileMap) throws IOException {
        ResultBean resultBean = new ResultBean();
        String result = null;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加文件
        if (fileMap != null && !fileMap.isEmpty()) {
            fileMap.entrySet().forEach(entry -> {
                RequestBody requestBody = RequestBody.create(MediaType.parse(getMediaType(entry.getValue().getName())), entry.getValue());
                builder.addFormDataPart(entry.getKey(), entry.getValue().getName(), requestBody);
            });
        }
        //添加参数
        if (params != null && !params.isEmpty()) {
            params.entrySet().forEach(entry -> {
                builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
            });
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Response response = getOkHttpClient(proxy).newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                result = getStringValue(response);
            }
        } else {
            result = response.message();
        }
        resultBean.setResult(result);
        resultBean.setResponse(response);
        response.close();
        return resultBean;
    }

    /**
     * 获取mediaType
     * @Description:
     * @author: 王文彬
     * @Date: 2017-07-07 00:34:24
     * @param: fileName
     * @return: java.lang.String
     */
    private static String getMediaType(String fileName) {
        FileNameMap map = URLConnection.getFileNameMap();
        String contentTypeFor = map.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
    /**断点下载
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-27 14:00:18
     * @param: url
     * @param: savePath
     * @param: position
     * @param: userName
     * @param: password
     * @return: java.lang.String
     */
    private static void downloadBreakpoint(String url, String savePath, long position, final String userName, final String password, boolean proxy) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            OkHttpClient okHttpClient = getOkHttpClient(proxy);
            if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
                OkHttpClient.Builder builder = okHttpClient.newBuilder();
                builder.authenticator((route, response) -> {
                    String credential = Credentials.basic(userName, password);
                    return response.request().newBuilder().header("Authorization", credential).build();
                });
                okHttpClient = builder.build();
            }
            Request.Builder builder = new Request.Builder();
            boolean breakpoint = false;
            if (position > 0l) {//添加header信息
                String headerValue = "bytes=" + position + "-";
//                builder.addHeader(HttpRequestHeaderConstant.RANGE, headerValue);
                breakpoint = true;
            }
            Request request = builder.get().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    inputStream = body.byteStream();
                    fileOutputStream = new FileOutputStream(new File(savePath), breakpoint);
                    byte[] bytes = new byte[1024];
                    int readCount;
                    while ((readCount = inputStream.read(bytes)) > 0) {
                        fileOutputStream.write(bytes, 0, readCount);
                        fileOutputStream.flush();
                    }
                }
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CloseableUtils.close(inputStream);
            CloseableUtils.close(fileOutputStream);
        }
    }

    /**
     * 断点下载
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-27 14:31:05
     * @param: url
     * @param: savePath
     * @return: void
     */
    public static void downloadBreakpoint(String url, String savePath, boolean proxy) {
        downloadBreakpointWithAuthorization(url, savePath, null, null, proxy);
    }

    /**
     * 带授权的断点下载
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-27 14:33:37
     * @param: url
     * @param: savePath
     * @param: userName
     * @param: password
     * @return: void
     */
    public static void downloadBreakpointWithAuthorization(String url, String savePath, String userName, String password, boolean proxy) {
        File file = new File(savePath);
        long position = 0;
        if(file.exists()){
            position = file.length();
        }
        downloadBreakpoint(url, savePath, position, userName, password, proxy);
    }

    /**
     * 下载
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-07 09:40:21
     * @param: url
     * @param: savePath
     * @return: void
     */
    public static void download(String url, String savePath, boolean proxy) {
        downloadBreakpoint(url, savePath, 0l, null, null, proxy);
    }

    /**
     * 带授权的下载
     * @Description:
     * @author: wwb_89
     * @Date: 2017-07-26 16:18:33
     * @param: url
     * @param: savePath
     * @param: userName
     * @param: password
     * @return: void
     */
    public static void downloadWithAuthorization(String url, String savePath, final String userName, final String password, boolean proxy) {
        downloadBreakpoint(url, savePath, 0l, userName, password, proxy);
    }

}
