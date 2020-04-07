package com.chaoxing.MutiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadLocal {


    static ThreadLocal<String> tl = new ThreadLocal<>();
    static int x = 0;

    public static class ParseDate implements Runnable {

        int i = 0;


        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (tl.get() == null) {
                tl.set(Thread.currentThread().getId() +"==============="+ Thread.currentThread().getName());
                x++;
                System.out.println("-----------------------------------" + x + "----------------------------------");
            }

            System.out.println(i + "===============" + tl.get());
            tl.remove();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }
        es.shutdown();
    }

}
