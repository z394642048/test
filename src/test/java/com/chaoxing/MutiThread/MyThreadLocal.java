package com.chaoxing.MutiThread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadLocal {

//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> tl=new ThreadLocal<SimpleDateFormat>();
    static int x=0;
    public static class ParseDate implements Runnable {

        int i=0;


        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if(tl.get()==null){
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    x++;
                    System.out.println("-----------------------------------"+x+"----------------------------------");
                }
                Date t = tl.get().parse("2015-03-29 18:48:" + i % 60);

//                Date t = sdf.parse("2015-03-29  18:48:" + i % 60);
                System.out.println(i+":"+t);
                tl.remove();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
    }

}
