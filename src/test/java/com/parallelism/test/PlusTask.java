package com.parallelism.test;

public class PlusTask implements Runnable {

    public volatile static long k = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(k);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            k++;
        }
    }
}
