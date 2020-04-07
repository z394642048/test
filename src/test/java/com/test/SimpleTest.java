package com.test;

import org.junit.Test;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

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
    @Test
    public void test1() {
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();

    }
}
