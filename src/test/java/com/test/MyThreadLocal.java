package com.test;

import org.junit.Test;

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
                }
            } );
        }
        for (int i = 0; i < 10; i++) {
            System.out.println( local.get() );
        }

    }
}
