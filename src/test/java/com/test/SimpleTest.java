package com.test;

import org.junit.Test;

import java.util.LinkedHashMap;

public class SimpleTest {

    @Test
    public void test() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("xx2","yyy");
        map.put("xx1","yyy");
        map.put("xx3","yyy");
        map.put("xx4","yyy");
        System.out.println(map);
        String xx = map.get("xx");
    }
}
