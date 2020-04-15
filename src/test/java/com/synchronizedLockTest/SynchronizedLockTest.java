package com.synchronizedLockTest;

import com.chaoxing.test.model.User;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 测试锁synchronized锁的升降级别(只能用run模式，不能用debug模式)
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           01 54 4d ba (00000001 01010100 01001101 10111010) (-1169337343)
 * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * 说明：
 * object header包含8个byte的mark word和4个byte的klass pointer
 * 其中，header中前8个字节按照平时习惯的从高位到低位的展示为：
 * 00000000 00000000 00000000 00000000 10111010 01001101 01010100 00000001
 * 最后3位中倒数第三位的0-表示无锁，2-表示偏向锁，最后两位中，01-表示偏向锁（结合倒数第三位看），00-表示轻量级锁，10-表示重量级锁，11-表示GC标记信息
 * 这里的最后3位是001，无锁状态，真正偏向锁是101。
 */
public class SynchronizedLockTest {

    /**
     * 无锁状态
     */
    @Test
    public void test() {
        Object object = new Object();
        System.out.println("hash: " + object.hashCode());
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }


    /**
     * 匿名偏向锁和偏向锁！
     * <p>
     * 第一次打印为匿名偏向，第二次偏向锁指向了main线程
     * <p>
     * 注意：用run启动程序，不要用debug，实验的时候，用debug启动，第二次打印直接升级轻量级锁。
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        /*
         * JVM启动时会进行一系列的复杂活动，比如装载配置，系统类初始化等等。在这个过程
         * 中会使用大量synchronized关键字对对象加锁，且这些锁大多数都不是偏向锁。为了
         * 减少初始化时间，JVM默认延时加载偏向锁。这个延时的时间大概为4s左右，具体时间
         * 因机器而异。当然我们也可以设置JVM参数 -XX:BiasedLockingStartupDelay=0 来取消
         * 延时加载偏向锁。
         * */
        //等待jvm开启偏向锁
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 轻量级锁
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        for (int i = 0; i < 1; i++) {
            Thread t = new Thread(() -> print(o));
            t.start();
        }
        Thread.sleep(5000);
    }

    /**
     * 重量级锁
     */
    @Test
    public void test3() throws InterruptedException {
        Object o = new Object();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                print(o);
            });
            t.start();
        }
        Thread.sleep(5000);
    }

    /**
     * 匿名偏向锁和偏向锁、轻量级锁、重量级锁
     */
    @Test
    public void test4() throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println("--------无锁---begin-----");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("--------无锁---end-----");
        Thread.sleep(5000);
        synchronized (o) {
            System.out.println("--------偏向锁---begin-----");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            System.out.println("--------偏向锁---end-----");
        }
        Thread.sleep(5000);
        for (int i = 0; i < 1; i++) {
            Thread t = new Thread(() -> {
                print(o, "轻量级锁");
            });
            t.start();
        }
        Thread.sleep(5000);
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                print(o, "重量级锁");
            });
            t.start();
        }
        Thread.sleep(5000);
    }


    private static void print(Object o, String name) {
        synchronized (o) {
            System.out.println("--------" + name + "---begin-----");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            System.out.println("--------" + name + "---end-----");
        }
    }

    private static void print(Object o) {
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 轻量级锁
     *
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                synchronized (o) {
                    System.out.println(Thread.currentThread().getId());
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }
            });
            t.start();
            Thread.sleep(5000);
        }
    }
}
