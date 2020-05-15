package com.test;

import java.util.Scanner;

/**
 * 算法测试题
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int s = scanner.nextInt();
        int d = s - Math.abs(a) - Math.abs(b);
        if (d >= 0 && d % 2 == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        scanner.close();
    }

}