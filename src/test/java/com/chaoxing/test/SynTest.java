package com.chaoxing.test;

public class SynTest {

    private static int e;
    private static int f;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    static long l = System.currentTimeMillis();


    public void test() throws InterruptedException {
        b++;
        System.out.println(Thread.currentThread().getName() +"====="+b + "====前====普通方法========" + (System.currentTimeMillis() - l));
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName() +"====="+b + "====后====普通方法========" + (System.currentTimeMillis() - l));
    }


    public synchronized void test1() throws InterruptedException {
        a++;
        System.out.println(Thread.currentThread().getName() +"---"+a + "----前----同步方法-------" + (System.currentTimeMillis() - l));
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName() +"---"+a + "----后----同步方法-------" + (System.currentTimeMillis() - l));
    }


    public void test2() throws InterruptedException {
        synchronized (this) {
            c++;
            System.out.println(Thread.currentThread().getName() +"---"+c+ "---前-----同步代码块1-------" + (System.currentTimeMillis() - l));
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() +"---"+c + "---后-----同步代码块1-------" + (System.currentTimeMillis() - l));
        }
    }

    public void test3() throws InterruptedException {
        synchronized (SynTest.class) {
            d++;
            System.out.println(Thread.currentThread().getName() +"---"+d + "----前----同步代码块2-------" + (System.currentTimeMillis() - l));
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() +"---"+d + "----后----同步代码块2-------" + (System.currentTimeMillis() - l));
        }
    }

    public static synchronized void test4() throws InterruptedException {
        e++;
        System.out.println(Thread.currentThread().getName() +"---"+e + "----前----同步静态方法-------" + (System.currentTimeMillis() - l));
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName() +"---"+e + "----后----同步静态方法-------" + (System.currentTimeMillis() - l));
    }


    /**
     * static修饰的方法内部不能同步对象
     * @throws InterruptedException
     */
/*
    public static void test5() throws InterruptedException {
        synchronized (this) {
            f++;
            System.out.println(Thread.currentThread().getName() +"---"+f+ "---前-----同步代码块1-------" + (System.currentTimeMillis() - l));
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() +"---"+f + "---后-----同步代码块1-------" + (System.currentTimeMillis() - l));
        }
    }  */
    public static void test6() throws InterruptedException {
        synchronized (SynTest.class) {
            f++;
            System.out.println(Thread.currentThread().getName() +"---"+f+ "---前-----同步静态代码块-------" + (System.currentTimeMillis() - l));
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() +"---"+f + "---后-----同步静态代码块-------" + (System.currentTimeMillis() - l));
        }
    }
}
