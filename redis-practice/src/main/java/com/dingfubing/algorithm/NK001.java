package com.dingfubing.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class NK001 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[10];
        int i = 0;
        while (in.hasNextInt()) {
            int i1 = in.nextInt();
            if (i1 == 0) {
                break;
            }
            arr[i] = i1;
            i++;
        }

        for (int temp : arr) {
            if (temp == 0) {
                break;
            }
            int res = 0;
            while (temp/3>0) {
                res += temp / 3;
                temp = temp / 3 + temp % 3;
            }
            if (temp == 2) {
                res++;
            }
            System.out.println(res);
        }
    }


}
