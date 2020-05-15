package com.test;

import java.util.Scanner;

/**
 * 算法练习题
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int member = scanner.nextInt();
        for (int i = 0; i < member; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();
            if (a >= 60 && b >= 60 && c >= 90 && d >= 90 && a + b + c + d >= 310) {
                if (a + b + c + d >= 350) {
                    System.out.println("Gongfei");
                } else {
                    System.out.println("Zifei");
                }
            } else {
                System.out.println("Fail");
            }
        }
    }

}