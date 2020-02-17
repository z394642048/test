package com.test;

import org.junit.Test;

/**
 * 验证int和Integer区别
 */
public class IntAndInteger {


    @Test
    public void tset1() {
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.println(i == j); //false

        Integer i1 = new Integer(100);
        Integer j1 = 100;
        System.out.println(i1 == j1); //false

        //因为包装类Integer和基本数据类型int比较时，java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）
        Integer i2 = new Integer(100);
        int j2 = 100;
        System.out.println(i2 == j2); //true

        Integer i3 = 100;
        Integer j3 = 100;
        System.out.println(i3 == j3); //true

        Integer i4 = 128;
        Integer j4 = 128;
        System.out.println(i4 == j4); //false

        Integer i5 = new Integer(1218);
        int j5 = 1218;
        System.out.println(i5 == j5); //true
    }
}
