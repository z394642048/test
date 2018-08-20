package com.fileupload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fileupload.util.HttpClientUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileUploadTest {

    /**
     * 云盘上传url
     */
    public static final String CLOUD_UPLOAD_URL = "http://cs.ananas.chaoxing.com/upload?uid=-1&prdid=40";
    /**
     * 云盘获取数据信息url
     */
    public static final String CLOUD_QUERY_STATUS_URL = "http://cs.ananas.chaoxing.com/status/";

    /*
     * @Description 将文件上传云盘并返回objectId
     * @author 杨牛浩江
     * @date 2018-08-03
     * @param file
     * @return java.lang.String
     */
    public String cloud() throws Exception, IOException {
        final File file = new File("D:\\uploadtest\\170015298.mp4");
        String result = HttpClientUtils.upload(CLOUD_UPLOAD_URL, null, true, new HashMap<String, File>() {{
            put("file", file);
        }}).getResult();

        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("result");
        if ("1".equalsIgnoreCase(code)) {
            return jsonObject.getString("objectid");
        } else {
            throw new Exception(jsonObject.getString("msg"));
        }
    }

    /*
     * @Description 根据objectId返回json字符串
     * @author 杨牛浩江
     * @date 2018-08-03
     * @param objectId
     * @return java.lang.String
     */
    public String getJsonByObjectId(String objectId) throws Exception {
        String resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + objectId, false, null).getResult();
        return resultStr;
    }

    public static void main(String[] args) throws IOException {

        final File file = new File("C:\\Users\\Public\\Videos\\Sample Videos\\oceans.mp4");
        String result = HttpClientUtils.upload(CLOUD_UPLOAD_URL, null, true, new HashMap<String, File>() {{
            put("file", file);
        }}).getResult();
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("result");
        String objectid = jsonObject.getString("objectid");

        //{"msg":"The file has been exist!","result":1,"crc":"eda16cd6ea9b5072d85c175c6c601108",
        // "objectid":"8056fa912f8c901df5988db8c1ba991f","status":"exist"}
        //{"msg":"Upload success!","result":1,"crc":"7116358969270763772dc2c8f0384f37",
        // "objectid":"db652ff2002a30b713dcd2da6cd4cfc6","status":"success"}
        //{"mp3":"http://s1.ananas.chaoxing.com/video/a7/05/d8/db652ff2002a30b713dcd2da6cd4cfc6/mp3/",
        // "download":"http://d0.ananas.chaoxing.com/download/db652ff2002a30b713dcd2da6cd4cfc6",
        // "filename":"VID_20180815_141357.mp4","crc":"7116358969270763772dc2c8f0384f37","httphd":"http://s1.ananas
        // .chaoxing.com/video/a7/05/d8/db652ff2002a30b713dcd2da6cd4cfc6/hd.mp4","length":1087300,
        // "http":"http://s1.ananas.chaoxing.com/video/a7/05/d8/db652ff2002a30b713dcd2da6cd4cfc6/sd.mp4",
        // "screenshot":"http://p2.ananas.chaoxing.com/video/a7/05/d8/db652ff2002a30b713dcd2da6cd4cfc6/snapshot.jpg",
        // "thumbnails":"http://p2.ananas.chaoxing.com/video/a7/05/d8/db652ff2002a30b713dcd2da6cd4cfc6/thumbnails/",
        // "objectid":"db652ff2002a30b713dcd2da6cd4cfc6","httpmd":"http://s1.ananas.chaoxing
        // .com/video/a7/05/d8/db652ff2002a30b713dcd2da6cd4cfc6/md.mp4","status":"success"}

        String resultStr = HttpClientUtils.get(CLOUD_QUERY_STATUS_URL + objectid, true,null).getResult();
        System.out.println(resultStr);
        JSONObject json = JSON.parseObject(resultStr);
        String url=json.getString("http");
        System.out.println(json);
        //{"download":"http://d0.ananas.chaoxing.com/download/8056fa912f8c901df5988db8c1ba991f",
        // "filename":"9.4%E6%94%BE%E5%81%87%E5%95%A6+.m4v","crc":"eda16cd6ea9b5072d85c175c6c601108",
        // "length":20978684,"objectid":"8056fa912f8c901df5988db8c1ba991f"}
    }
}
