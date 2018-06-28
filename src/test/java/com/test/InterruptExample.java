package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InterruptExample {
    private static class MyThread2 extends Thread {
        @Override
        public void run() {
//            while (!interrupted()) {
            while (!isInterrupted()) {
                System.out.println(" 666");
            }
            System.out.println("Thread end");
        }
    }
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread2 = new MyThread2();
//        thread2.start();
//        Thread.sleep( 1000 );
//        thread2.interrupt();
//    }
public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> {
        try {
            Thread.sleep(2000);
            System.out.println("Thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
//    executorService.shutdownNow();
    executorService.shutdown();
    System.out.println("Main run");

    Future<?> future = executorService.submit(() -> {
        // ..
    });
    future.cancel(true);
}
}
