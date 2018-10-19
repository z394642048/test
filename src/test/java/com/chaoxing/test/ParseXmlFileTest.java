package com.chaoxing.test;

import com.chaoxing.test.service.IParseXmlFileService;
import com.chaoxing.test.util.ParseXmlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;



public class ParseXmlFileTest {


    @Test
    public void test1() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1",1);
        Object o = map.get("2");
        System.out.println(o);

    }
}
