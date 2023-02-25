package com.dingfubing.algorithm;

import java.util.Scanner;

public class NK003 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String next = in.next();
        if (!next.startsWith("0x")) {
            System.out.println("");
        } else {
            // 从index=2的位置开始截取，包含index=2的位置
            next = next.substring(2);
            long res = 0;
            long octal = 1;
            for (int i = next.length() - 1; i >= 0; i--) {
                int temp;
                if (next.charAt(i) >= '0' && next.charAt(i) <= '9') {
                    temp = next.charAt(i) - '0';
                }else {
                    temp = next.charAt(i) - 'A' + 10;
                }
                res += octal * temp;
                octal *= 16;
            }
            System.out.println(res);
        }

    }
}
