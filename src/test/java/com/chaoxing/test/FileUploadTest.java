package com.chaoxing.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chaoxing.test.service.IFileUploadService;
import com.fileupload.util.HttpClientUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadTest {

    /**
     * 云盘上传url
     */
    public static final String CLOUD_UPLOAD_URL = "http://cs.ananas.chaoxing.com/upload?uid=-1&prdid=40";
    /**
     * 云盘获取数据信息url
     */
    public static final String CLOUD_QUERY_STATUS_URL = "http://cs.ananas.chaoxing.com/status/";

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 上传视频并将视频返回的url地址、objectId存到服务器中
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
//        上传部分
        final File folder = new File("D:\\科普视频\\xx");
        System.out.println(folder.getName());
        long begin = System.currentTimeMillis();
        File[] files = folder.listFiles();
        AtomicInteger integer = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);
        for (File file : files) {
            if (file.isDirectory()) {
                long l = System.currentTimeMillis();
                File[] files1 = file.listFiles();
                for (File file1 : files1)
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            long l1 = System.currentTimeMillis();
                            Map<String, Object> map = new HashMap<>();
                            String number = file1.getName();
                            if (number.contains("mp4")) {
                                number = number.split("\\.")[0];
                                Map<String, Object> videoName = fileUploadService.getVideoName(number);
                                String name = (String) videoName.get("videoName");
                                if (null != name && !"".equals(name)) {
                                    map.put("name", name);
                                    String result = null;
                                    try {
                                        result = HttpClientUtils.upload(CLOUD_UPLOAD_URL, null, true, new HashMap<String, File>() {{
                                            put("file", file1);
                                        }}).getResult();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    JSONObject jsonObject = JSON.parseObject(result);
                                    String objectid = jsonObject.getString("objectid");
                                    map.put("objectid", objectid);
                                    String resultStr = null;
                                    try {
                                        resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + objectid, true, null).getResult();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    JSONObject json = JSON.parseObject(resultStr);
                                    String url = json.getString("httphd");
                                    String duration = json.getString("duration");
                                    String status = json.getString("status");
                                    if ("success".equals(status)) {
                                        map.put("status", 1);
                                    } else {
                                        map.put("status", 0);
                                    }
                                    System.out.println(url);
                                    map.put("url", url);
                                    map.put("time", duration);
                                    int id = fileUploadService.addPltVideo(map);
                                    int seriesId = (Integer) videoName.get("id");
                                    String sequence = (String) videoName.get("sequence");
                                    fileUploadService.addMiddleTable(id, seriesId, Integer.valueOf(sequence));
                                    integer.incrementAndGet();
                                    System.out.println("上传一个视频时间：" + (System.currentTimeMillis() - l1) / 1000);
                                } else {
                                    count.incrementAndGet();
                                }
                            }
                        }
                    });
                System.out.println("上传" + file.getName() + "文件夹中视频时间：" + (System.currentTimeMillis() - l) / 1000);
            }
        }
        //防止程序提前结束
        //关闭线程池
        pool.shutdown();
        while (true) {
            //判断线程池是否关闭
            if (pool.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }
        System.out.println("没得到名字的数量：" + count);
        System.out.println("成功上传视频数量：" + integer);
        System.out.println("上传视频总时间：" + (System.currentTimeMillis() - begin) / 1000);
    }

    /**
     * 查看那些视频没有上传到
     */
    @Test
    public void test2() {
        File file = new File("E:\\新教育\\小学整本书");
        System.out.println(file.getName());
        List<String> listName = fileUploadService.getListName(file.getName());
        File[] files = file.listFiles();
        int i = 0;
        for (File file1 : files) {
            if (listName.contains(file1.getName())) {
//                System.out.println(file1.getName());
//                i++;
            } else {
                i++;
                System.out.println(file1.getName());
            }
        }
        System.out.println(i);

    }

    /**
     * 查询url没有的数据并赋值
     */
    @Test
    public void test3() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<HashMap<String, Object>> objects = fileUploadService.getNullUrl();
        for (HashMap<String, Object> object : objects) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    String objectId = (String) object.get("objectId");
                    String resultStr = null;
                    try {
                        resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + objectId, true, null).getResult();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject json = JSON.parseObject(resultStr);
                    String url = json.getString("http");
                    System.out.println(url);
                    fileUploadService.updateByUrl((Integer) object.get("id"), url);
                    System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
                }
            });
        }
        pool.shutdown();
        while (true) {
            //判断线程池是否关闭
            if (pool.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }

    }

    @Test
    public void test4() throws IOException {
        String resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + "6b239a2a3667be58aa91a18a5bc3d72c", true, null).getResult();
        JSONObject json = JSON.parseObject(resultStr);
        String url = json.getString("httphd");
        String duration = json.getString("duration");
        String objectid = json.getString("objectid");
        fileUploadService.updateByUrl(339, url);
        System.out.println(url);
    }


    /**
     * 查询url/time没有的数据并赋值
     */
    @Test
    public void test5() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<HashMap<String, Object>> objects = fileUploadService.getNoPass();
        for (HashMap<String, Object> object : objects) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    String objectId = (String) object.get("objectId");
                    String resultStr = null;
                    try {
                        resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + objectId, true, null).getResult();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject json = JSON.parseObject(resultStr);
                    HashMap<String, Object> map = new HashMap<>();
                    String url = json.getString("httphd");
                    map.put("url", url);
                    String time = json.getString("duration");
                    map.put("time", time);
                    map.put("id", object.get("id"));
                    if (url != null && !"".equals(url) && time != null && !"".equals(time)) {
                        map.put("status", 1);
                    }
                    System.out.println(url);
                    fileUploadService.updateVideo(map);
                    System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
                }
            });
        }
        pool.shutdown();
        while (true) {
            //判断线程池是否关闭
            if (pool.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }

    }

    @Test
    public void test6() throws IOException {
        String resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + "08672b97be1bdea8e332714b2e92d3a7", true, null).getResult();
        JSONObject json = JSON.parseObject(resultStr);
        HashMap<String, Object> map = new HashMap<>();
        String url = json.getString("httphd");
        map.put("url", url);
        String time = json.getString("duration");
        map.put("time", time);
        map.put("id", 756);
        if (url != null && !"".equals(url) && time != null && !"".equals(time)) {
            map.put("status", 1);
        }
        fileUploadService.updateVideo(map);
        System.out.println(url);
    }

    @Test
    public void test7() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "23434");
        map.put("url", "333333333");
        map.put("time", 5654);
        map.put("status", 1);
        map.put("objectid", "234234234");
        map.put("id", null);
        int id = fileUploadService.addPltVideo(map);
        System.out.println(id);
    }

    /**
     * 视频直播
     *
     * @throws IOException
     */
    @Test
    public void test8() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
//        上传部分
        final File folder = new File("D:\\直播新资源20180918\\xx");
        System.out.println(folder.getName());
        long begin = System.currentTimeMillis();
        File[] files = folder.listFiles();
        AtomicInteger integer = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);
        for (File file : files) {
            if (file.isDirectory()) {
                File[] files1 = file.listFiles();
                for (File file1 : files1)
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String number = file1.getName();
                            if (number.contains("mp4")) {
                                number = number.split("\\.")[0];
                                Map<String, Object> map = fileUploadService.getVideoName(number);
                                String result = null;
                                try {
                                    result = HttpClientUtils.upload(CLOUD_UPLOAD_URL, null, true, new HashMap<String, File>() {{
                                        put("file", file1);
                                    }}).getResult();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                JSONObject jsonObject = JSON.parseObject(result);
                                String objectid = jsonObject.getString("objectid");
                                map.put("objectid", objectid);
                                String resultStr = null;
                                try {
                                    resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + objectid, true, null).getResult();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                JSONObject json = JSON.parseObject(resultStr);
                                String url = json.getString("httphd");
                                map.put("url", url);
                                String duration = json.getString("duration");
                                map.put("time", duration);
                                map.put("isRecommend", 0);
                                String status = json.getString("status");
                                if ("success".equals(status)) {
                                    map.put("status", 1);
                                } else {
                                    map.put("status", 0);
                                }
                                System.out.println(url);
                                fileUploadService.addPltVideo(map);
                                integer.incrementAndGet();
                            }
                        }
                    });
            }
        }
        //防止程序提前结束
        //关闭线程池
        pool.shutdown();
        while (true) {
            //判断线程池是否关闭
            if (pool.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }
        System.out.println("没得到名字的数量：" + count);
        System.out.println("成功上传视频数量：" + integer);
        System.out.println("上传视频总时间：" + (System.currentTimeMillis() - begin) / 1000);
    }

}


















