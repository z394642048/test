package com.parallelism.test;

public class TestThread1 implements  Runnable {
    @Override
    public void run() {
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        testThread1.run();
        Thread thread = new Thread(testThread1);
        thread.start();
        try {
            thread.join();
            System.out.println("===================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
