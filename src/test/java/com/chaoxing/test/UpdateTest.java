package com.chaoxing.test;

import com.chaoxing.test.model.Student;
import com.chaoxing.test.model.User;
import com.chaoxing.test.service.IFanyaUserDetailService;
import com.chaoxing.test.service.IOpenAcaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith( SpringRunner.class )
@SpringBootTest
public class UpdateTest {


    @Autowired
    private IOpenAcaService openAcaService;

    @Autowired
    private IFanyaUserDetailService fanyaUserDetailService;

    @Test
    public void index() {

        List<String> fid = openAcaService.getFid();
        StringBuilder sb = new StringBuilder();

        for (String s : fid) {
            System.out.println(s);
            sb.append(s).append(",");
        }
        System.out.println(sb);

//        fanyaUserDetailService.updateAid(fid);

    }


    //验证地址
    @Test
    public void xx() {

        Map<Object, Object> mapTest = new HashMap<>();
        System.out.println(mapTest);
        mapTest.put("xx", "xx");
        System.out.println(mapTest);

        List<Object> list = new ArrayList<>();
        System.out.println(list);
        list.add("fsdfsdf");
        System.out.println(list);

        User student = new User();
        System.out.println(student);
        student.setName("dfsdf");
        System.out.println(student);


    }

    @Test
    public void x1x() {

        System.out.println(new Date());
        Properties p = System.getProperties();
        p.list(System.out);
        System.out.println("--- Memory Usage:");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory = "
                + rt.totalMemory()
                + " Free Memory = "
                + rt.freeMemory());
    }

    @Test
    public void xx1() {

        byte ss= (byte) 101010010;

        //32=2^5
        int x = 32;
        int y = ss >> 3;
        int z = ss << 3;

        System.out.println(y);
        System.out.println(z);

    }

    @Test
    public void xx2() {

        User user = new User();
        user.setName("1111111111");
        User user2=user;
        user2.setPassword("222222222222");
        System.out.println(user);
        System.out.println(user2);

        Integer x=7;
        Integer y=x;
        y=6;
        System.out.println(x+"======="+y);

        String s="xxxxxxxxxxx";
        String sss=s;
        sss="555555555";
        System.out.println(s+"-------------"+sss);

        String s1=new String("xxxxxxxxxxx");
        String sss1=s1;
        sss1="555555555";
        System.out.println(s1+"-------------"+sss1);

    }



    @Test
    public void xx3() {
        User user;
//        System.out.println(user);
    }

}
