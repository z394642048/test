package com.base;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestTryCatch {

    @Test
    public void test1() {
        AtomicInteger i = new AtomicInteger();
//        System.out.println("xxx:" + fun(i));
        System.out.println("xxx:" + fun2(new Integer(0)));
    }

    private AtomicInteger fun(AtomicInteger i) {
        try {
            i.set(1);
            System.out.println("yyy:" + i);
            return i;
        } catch (Exception e) {
            i.set(2);
            System.out.println("zyx:" + i);
            return i;
        } finally {
            i.set(3);
            System.out.println("zzz:" + i);
//            return i;
        }
    }

    private Integer fun2(Integer i) {
        try {
            System.out.println("yyy:" + i);
            return i;
        } catch (Exception e) {
            i = 2;
            System.out.println("zyx:" + i);
            return i;
        } finally {
            i = new Integer(3);
            System.out.println("zzz:" + i);
//            return i;
        }
    }

}