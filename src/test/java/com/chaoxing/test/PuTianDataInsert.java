package com.chaoxing.test;

import com.chaoxing.test.service.impl.OpenOrgServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest
public class PuTianDataInsert {

    @Autowired
    private OpenOrgServiceImpl openOrgService;

    /**
     * 将莆田市新增的机构添加到对应的区域表中
     */
    @Test
    public void test3() {
        //城厢区
//        String path="C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\城厢区";
        //荔城区
//        String path="C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\荔城区\\荔城区";
        //秀屿区
//        String path = "C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\秀屿区";
        //仙游县
//        String path = "C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\仙游县";
        //涵江区
//        String path = "C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\涵江区";
        //市直
        String path = "C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\市直";


        File file = new File(path);
        String[] fileName = file.list();


        System.out.println(fileName.length);
        StringBuffer sb = new StringBuffer();
        List<String> all = openOrgService.getAll();
        int i = 0;
        for (String allName : all) {
            for (String s : fileName) {
                if (allName.indexOf(s) != -1) {
                    sb.append(",'").append(allName).append("'");
                    i++;
                }
            }
        }
        System.out.println(i);
//        for (String s : fileName) {
//            sb.append(",'").append(s).append("'");
//
//        }


        String s = sb.toString();
        String orgNames = s.substring(1);
        System.out.println(orgNames);

        //莆田市
        openOrgService.insertCity(orgNames, "350300");

        //城厢区
//        openOrgService.insertAll(orgNames,"350302");

        //荔城区
//        openOrgService.insertAll(orgNames,"350304");

        //秀屿区
//        openOrgService.insertAll(orgNames, "350305");

//        仙游县
//        openOrgService.insertAll(orgNames, "350322");

        //涵江区
//        openOrgService.insertAll(orgNames, "350303");


    }


}
