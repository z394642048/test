package com.chaoxing.test;

import org.junit.Test;

public class NormalTest {


    @Test
    public void test1() {

        Integer integer = new Integer(7);
        Integer integer1 = new Integer(7);
        System.out.println(integer == integer1);

        Integer integer3 = 7;
        Integer integer4 = 7;
        System.out.println(integer3 == integer4);

        Integer integer5 = 1117;
        Integer integer6 = 1117;
        System.out.println(integer5 == integer6);


    }

    /**
     * 结论：
     * ①无论任何形式的锁，只要是普通方法就都不会被锁住；
     * ②同步类和同步对象不会相互影响；
     * ③同步对象的两种方式会相互影响（同步方法、同步代码块this）
     */
    @Test
    public void test2() throws InterruptedException {
        SynTest synTest = new SynTest();

        Thread[] threads = new Thread[50];
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
//                    synTest.test1();//不影响普通方法
//                    synTest.test2();//不影响普通方法
//                    synTest.test3();//不影响普通方法
                    synTest.test4();//不影响普通方法
//                    synTest.test6();//不影响普通方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }


        Thread[] threads1 = new Thread[100];
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
//                    synTest.test();
//                    synTest.test1();
//                    synTest.test2();
                    synTest.test3();
//                    synTest.test4();
//                    synTest.test6();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 100; i++) {
            threads1[i] = new Thread(runnable1);
            threads1[i].start();
        }


        Thread.sleep(60000);
    }

}
