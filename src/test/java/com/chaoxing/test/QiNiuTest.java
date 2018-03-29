package com.chaoxing.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sun.deploy.net.URLEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiNiuTest {

    /**
     * 图片上传
     */
    @Test
    public void test1() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "EN9A5uCDBEK6RhJJJMRtNcqAOtktxWFEO6oC8ftc";
        String secretKey = "XabWVs1pvjSXGRjlvWUIxP1_12S6crUW0W3QPWdM";
        String bucket = "bucket";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\Public\\Pictures\\Sample Pictures\\20180108170925222.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "999";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //上面设置的key值，不设置的情况下，以文件内容的hash值作为文件名
            System.out.println(putRet.key);
            //该文件在七牛云中的名字，是文件内容的hash值作为文件名
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * 图片下载
     */
    @Test
    public void test2() throws UnsupportedEncodingException {
        String fileName = "FtJ1ZOKwCDZ9BOkOP6Hl74ZIGaRK";
        String domainOfBucket = "http://oz78h8r3j.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

        String accessKey = "EN9A5uCDBEK6RhJJJMRtNcqAOtktxWFEO6oC8ftc";
        String secretKey = "XabWVs1pvjSXGRjlvWUIxP1_12S6crUW0W3QPWdM";
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        //图片路径
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);

        System.out.println(finalUrl);

    }


}
