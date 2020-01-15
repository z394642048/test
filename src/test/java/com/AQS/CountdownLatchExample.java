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
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( "run.." );
                countDownLatch.countDown();
                System.out.println( "---------" );
                try {
                    countDownLatch.await();
                    System.out.println( Thread.currentThread().getName() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } );
        }
        long l = System.currentTimeMillis();

        Long call = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                countDownLatch.await();
                System.out.println( Thread.currentThread().getName() + "6666" );
                Thread.sleep( 6000 );
                return Thread.currentThread().getId() + 998L;
            }
        }.call();
        System.out.println( call );
        FutureTask<String> futureTask = new FutureTask<>( new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("oooooooooooooo");
                Thread.sleep( 3000 );
                return "333333333";
            }
        } );
        new Thread( futureTask ).start();
        System.out.println(System.currentTimeMillis()-l+"=======");
        String s = futureTask.get();
        System.out.println(s);
        System.out.println(System.currentTimeMillis()-l+"------");

        countDownLatch.await();
        System.out.println( "end" );
        executorService.shutdown();
    }
}
