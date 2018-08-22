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

    @Test
    public void test1() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
//        上传部分
        final File folder = new File("E:\\新教育资源");
        System.out.println(folder.getName());
        long begin = System.currentTimeMillis();
        File[] files = folder.listFiles();
        AtomicInteger integer = new AtomicInteger(0);
        for (File file : files) {
            if (file.isDirectory()) {
                long l = System.currentTimeMillis();
                File[] files1 = file.listFiles();
                for (File file1 : files1) {
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            long l = System.currentTimeMillis();
                            Map<String, String> map = new HashMap<>();
                            map.put("folderName", file.getName());
                            map.put("fileName", file1.getName());
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
                            String url = json.getString("http");
                            System.out.println(url);
                            map.put("url", url);
                            fileUploadService.addVideo(map);
                            integer.incrementAndGet();
                            System.out.println("上传一个视频时间：" + (System.currentTimeMillis() - l) / 1000);
                        }
                    });
                }
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
        System.out.println("成功上传视频数量：" + integer);
        System.out.println("上传视频总时间：" + (System.currentTimeMillis() - begin) / 1000);
    }

    @Test
    public void test2(){
        File file = new File("E:\\新教育\\小学晨诵\\五年级");
        System.out.println(file.getName());
        List<String> listName = fileUploadService.getListName(file.getName());
        File[] files = file.listFiles();
        int i=0;
        for (File file1 : files) {
            if (listName.contains(file1.getName())){
//                System.out.println(file1.getName());
            }else {
                i++;
                System.out.println(file1.getName());
            }
        }
        System.out.println(i);

    }


}
