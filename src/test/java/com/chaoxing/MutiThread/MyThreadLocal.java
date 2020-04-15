package com.chaoxing.MutiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadLocal {


    static ThreadLocal<String> tl = new ThreadLocal<>();
    static volatile int x = 0;

    public static class ParseDate implements Runnable {

        int i = 0;


        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (tl.get() == null) {
                x++;
                System.out.println(i + "-----------------------------------" + x + "----------------------------------");
            }
            tl.set(Thread.currentThread().getId() + "===============" + Thread.currentThread().getName());

            System.out.println(tl.get());
            if (i == 9) {
                tl.get();
                tl.set(Thread.currentThread().getId() + "=======1111========" + Thread.currentThread().getName());
                tl.get();
                tl.remove();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            es.execute(new ParseDate(i));
        }
        es.shutdown();
    }

}
