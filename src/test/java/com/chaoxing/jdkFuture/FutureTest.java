package com.chaoxing.jdkFuture;

import org.junit.Test;

import java.util.concurrent.*;

//适用于任务之间没有关系的时候运用
public class FutureTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        //构造FutureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //开启线程进行RealData的call()执行
        executor.submit(future);
        System.out.println("请求完毕");
        try {
            //做额外的数据操作，用sleep代替处理其它业务逻辑
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果此时
        System.out.println("数据 =" + future.get());
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 10; i++) {
                    sb.append("b");
                }
                try {
                    System.out.println("休息一下");
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                return sb.toString();
            }
        });
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(future);
        System.out.println("请求完毕");
        try {
            //做额外的数据操作，用sleep代替处理其它业务逻辑
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 =" + future.get());
    }
}
