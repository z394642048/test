package com.AQS;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadLocal {

    @Test
    public void test1() {
        ThreadLocal<Object> local = new ThreadLocal<>();
        ExecutorService executor = Executors.newFixedThreadPool( 10 );
        for (int i = 0; i < 10; i++) {
            executor.execute( new Runnable() {
                @Override
                public void run() {
                    local.set( "值：" + Thread.currentThread().getName() );
                    Object o1 = local.get();
                    local.set( "值：" + Thread.currentThread().getId() );
                    Object o2 = local.get();
                }
            } );
        }
    }

    @Test
    public void test2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool( 10 );
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        final int[] j = {0};
        for (int i = 0; i < 100000; i++) {
            executorService.execute( new Runnable() {
                @Override
                public void run() {
                    int i1 = j[0]++;
                    map.put( "x",i1 );
                }
            } );
        }
        final int[] k = {0};
        for (int i = 0; i < 100000; i++) {
            executorService.execute( new Runnable() {
                @Override
                public void run() {
                    int i1 = k[0]++;
                    hashMap.put( "y",i1 );
                }
            } );
        }
        Thread.sleep( 3000 );
        System.out.println(map);
        System.out.println(hashMap);
    }
}
