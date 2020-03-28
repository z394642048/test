package com.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {

    //最多允许5条线程通行
    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            //获取线程
            semp.acquire();
            System.out.println(Thread.currentThread().getId() + ":获取线程!开始执行业务。");
            //业务就是持有线程休息两秒
            Thread.sleep(20000);
            //释放线程
            semp.release();
            System.out.println(Thread.currentThread().getId() + ":释放线程！业务执行完毕。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建有20个线程的想吃
        ExecutorService exeu = Executors.newFixedThreadPool(20);
        SemaphoreTest test = new SemaphoreTest();
        for (int i = 0; i < 20; i++) {
             exeu.submit(test);
//            System.out.println(submit);
        }
//        System.out.println("---------");
        exeu.shutdown();
    }

}
