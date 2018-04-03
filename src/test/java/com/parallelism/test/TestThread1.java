package com.parallelism.test;

public class TestThread1 implements  Runnable {
    @Override
    public void run() {
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TestThread1());
        thread.start();
        try {
            thread.join();
            System.out.println("===================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
