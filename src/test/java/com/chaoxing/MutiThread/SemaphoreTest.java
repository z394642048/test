package com.chaoxing.MutiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {

    final Semaphore semp= new Semaphore(5);

    @Override
    public void run() {
        try {
            semp.acquire();
            System.out.println(Thread.currentThread().getId()+":done!");
            Thread.sleep(2000);
            semp.release();
            System.out.println(Thread.currentThread().getId()+":over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exeu = Executors.newFixedThreadPool(20);
        SemaphoreTest test = new SemaphoreTest();
        for (int i = 0; i < 20; i++) {
            Future<?> submit = exeu.submit(test);
            System.out.println(submit);
        }
        System.out.println("---------");

    }

}
