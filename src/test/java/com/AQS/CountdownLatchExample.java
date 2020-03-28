package com.AQS;

import java.util.concurrent.*;

public class CountdownLatchExample {

    public static void main(String[] args) {
        final int totalThread = 10;
        //初始化计数器
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("——执行分支业务——");
                //计算执行到此的线程数
                countDownLatch.countDown();
                try {
                    System.out.println("----陷入等待-----");
                    //等待，直到等待的线程数达到初始化数量
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + "======分支结束都结束，执行主业务=======");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
