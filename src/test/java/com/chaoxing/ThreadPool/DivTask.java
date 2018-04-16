package com.chaoxing.ThreadPool;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.*;

public class DivTask implements Runnable {

    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re=a/b;
        System.out.println(re);
    }

    public static void main(String[] args) {

        /*
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
           *//*
            //方式①，不能打印错误
            pools.submit(new DivTask(100, i));
            *//*
           *//*
            //方式②，打印部分错误
            Future<?> re = pools.submit(new DivTask(100, i));
            try {
                re.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            *//*

           //方式③，打印部分错误
           pools.execute(new DivTask(100,i));

        }
        */

        //方式④，自定义一个线程池
        TraceThreadPoolExecutor pool = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            pool.execute(new DivTask(100,i));
        }



    }

}
