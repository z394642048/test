package com.chaoxing.test;

import com.chaoxing.test.service.impl.OpenOrgServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith( SpringRunner.class )
@SpringBootTest
public class DataInsert {

    @Autowired
    private OpenOrgServiceImpl openOrgService;

    /**
     * 将莆田市新增的机构添加到对应的区域表中
     */
    @Test
    public void test3() {
        //城厢区
//        String path="C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\第一批--城厢区";
//        荔城区
//        String path="C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\第二批--荔城区、指导校、湄洲岛\\荔城区";

        String path = "C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\第二批--荔城区、指导校、湄洲岛\\秀屿区 湄洲";


        File file = new File(path);
        String[] fileName = file.list();
        StringBuffer sb = new StringBuffer();
        for (String s : fileName) {
            sb.append(",'").append(s).append("'");
        }
        String s = sb.toString();
        String orgNames = s.substring(1);
        //城厢区
//        openOrgService.insertAll(orgNames,"350302");
        //荔城区
//        openOrgService.insertAll(orgNames,"350304");
        //秀屿区
        openOrgService.insertAll(orgNames, "350305");


        //莆田市
        openOrgService.insertCity(orgNames, "350300");
    }

}
