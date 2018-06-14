package com.parallelism.cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet();
            }
        }
    }
    @Test
    public void test1() throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int j = 0; j < 10; j++) {
            ts[j]=new Thread(new AddThread());
        }
        for (int j = 0; j < 10; j++) {
            ts[j].start();
        }
        for (int j = 0; j < 10; j++) {
            ts[j].join();
        }
        System.out.println(i);
    }

}
