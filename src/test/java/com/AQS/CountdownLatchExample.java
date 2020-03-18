package com.AQS;

import java.util.concurrent.*;

public class CountdownLatchExample {

    public static void main(String[] args) throws Exception {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch( totalThread );
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute( () -> {
                try {
                    Thread.sleep( 100 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( "run.." );
                countDownLatch.countDown();
                System.out.println( "---------" );
                try {
                    countDownLatch.await();
                    System.out.println( Thread.currentThread().getName() +"=============");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } );
        }
        System.out.println( "end" );
        executorService.shutdown();
    }
}
