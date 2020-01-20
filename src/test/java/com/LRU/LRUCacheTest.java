package com.LRU;

import java.util.Map;

/**
 * 测试LRUCache
 * 
 * @author leiqian
 *
 */
public class LRUCacheTest {

    public static void print(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
        System.out.println("-----------------------");
    }

    public static void main(String[] args) {
        LRUCache<String, String> map = new LRUCache<>(5);

        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        map.put("k5", "v5");
        map.put("k6", "v6");
        print(map); // 应该移除了k1

        map.get("k2");
        map.put("k7", "v7");
        print(map); // 应该移除了k3

        map.get("k4");
        map.put("k8", "v8");
        print(map); // 应该移除了k5

    }
}

