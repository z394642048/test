package com.test;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 验证int和Integer区别
 */
public class IntAndInteger {


    @Test
    public void test1() {
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

    @Test
    public void test2() {
        int count = 0;
        count++;
        System.out.println(count);


    }

    @Test
    public void test3() {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        List<Integer> xxx = Arrays.asList(12, 32, 43, 5, 11);
        List<Integer> yyy = Arrays.asList(16, 23, 32, 22, 11);

        List<List<Integer>> list1 = new LinkedList<>();
        list1.add(integers);
        list1.add(integers);
        list1.add(integers);
        List<List<Integer>> list2 = new LinkedList<>();
        list2.add(integers);
        list2.add(xxx);
        list2.add(integers);
        list2.add(integers);
        List<List<Integer>> list3 = new LinkedList<>();
        list3.add(integers);
        list3.add(yyy);
        list3.add(integers);
        list3.add(integers);

        List<List<List<Integer>>> list4 = new LinkedList<>();
        list4.add(list1);
        list4.add(list2);
        list4.add(list3);
        xxxx:
        for (List<List<Integer>> lists : list4) {
            first:
            for (List<Integer> list : lists) {
                for (Integer integer : list) {
                    System.out.print(integer + "-");
                    if (integer == 5) {
                        System.out.println("跳出2层");
                        break first;
                    }
                    if (integer == 22) {
                        System.out.println("跳出最外层循环");
                        break xxxx;
                    }
                }
                System.out.println("==========222222222222===========");
            }
            System.out.println("-------333333333333----------");
        }

    }
}
