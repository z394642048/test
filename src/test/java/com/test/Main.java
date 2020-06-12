package com.test;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
        int max = 0;
        while (n-- > 0) {
            int x = scanner.nextInt();
            int t = scanner.nextInt();
            if (!map.containsKey(t)) {
                map.put(t, new int[11]);
            }
            map.get(t)[x] = 1;
            if (t > max)
                max = t;
        }
        System.out.println(solve(map, max));
    }

    private static int solve(HashMap<Integer, int[]> map, int max) {
        int result = 0;
        boolean[] pos = new boolean[11];
        pos[5] = true;
        int[] res = new int[11];
        for (int i = 1; i <= max; i++) {
            boolean[] newpos = new boolean[11];
            int[] newres = new int[11];
            for (int j = 0; j < 11; j++) {
                if (pos[j]) {
                    for (int k = j > 0 ? j - 1 : 0; k <= (j < 10 ? j + 1 : 10); k++) {
                        newpos[k] = true;
                        int[] v = map.get(i);
                        if (v == null)
                            v = new int[11];
                        int value = res[j] + v[k];
                        if (value > newres[k])
                            newres[k] = value;
                    }
                }
            }
            pos = newpos;
            res = newres;
        }
        for (int i = 0; i < 11; i++) {
            if (res[i] > result)
                result = res[i];
        }
        return result;
    }

}