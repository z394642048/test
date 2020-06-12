package com.chaoxing.MutiThread;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class IntLock {

    int x = 100;

    @Test
    public void test1() throws InterruptedException {

        ReentrantLock r1 = new ReentrantLock();
        Condition c1 = r1.newCondition();
        Thread thread = new Thread(() -> {
            r1.lock();
            for (int i = 0; i < x; i++) {
                System.out.println(i);
                if (i == 50) {
                    try {
                        System.out.println("等一下");
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("0000000000000000");
            r1.unlock();
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            r1.lock();
            for (int i = 0; i < x; i++) {
                System.out.println(i + "---" + Thread.currentThread().getName());
            }
            System.out.println("over");
            r1.unlock();
        });
        thread1.start();

        r1.lock();
        c1.signal();
        r1.unlock();
        Thread.sleep(100);
        System.out.println("-----------");
    }

    @Test
    public void test2() {
        //查看当前线程的中断状态，并将中断状态设置成false
        System.out.println("返回值：" + Thread.interrupted());
        //中断当前线程，即将当前中断状态设置成true
        Thread.currentThread().interrupt();
        //查看当前线程的中断状态，并将中断状态设置成false
        System.out.println("返回值：" + Thread.interrupted());
        System.out.println("返回值：" + Thread.interrupted());
        System.out.println("返回值：" + Thread.interrupted());
    }

}
