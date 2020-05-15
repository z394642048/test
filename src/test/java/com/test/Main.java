package com.test;

import org.junit.Test;

import java.util.*;

/**
 * 算法练习题
 */
public class Main {

    public static void main(String[] args) {
        int sum = 0;
        int max = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            sum += b - a;
            if (max < sum) {
                max = sum;
            }
        }
        System.out.println(max);
    }

    @Test
    public void test2() {
        int sum1 = 1;
        int sum2 = 1;
        int b = 5;
        int a = 8;
        System.out.println(sum1 += b);
        System.out.println(sum2 += -a + b);
        System.out.println(sum1);
        System.out.println(sum2);
    }
}