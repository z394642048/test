package com.parallelism.test;

public class MultiThread implements Runnable {
    public volatile static  long k=0;
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            k++;
        }
    }
}
