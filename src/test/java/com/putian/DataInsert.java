package com.putian;

import com.chaoxing.test.service.impl.OpenOrgServiceImpl;
import com.sun.glass.ui.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Application.class )
public class DataInsert {

    @Autowired
    private OpenOrgServiceImpl openOrgService;

    @Test
    public void test3() {
        String path = "C:\\Users\\Administrator\\Desktop\\超星工作相关\\莆田区域平台\\数据导入\\第一批--城厢区";
        File file = new File(path);
        String[] fileName = file.list();
        StringBuffer sb = new StringBuffer();
        for (String s : fileName) {
            System.out.println(s);
            sb.append(",").append(s);
        }
        String s = sb.toString();
        String orgNames = s.substring(1);
        openOrgService.insertAll(orgNames, "350302");
        System.out.println("xxx");

    }

}
