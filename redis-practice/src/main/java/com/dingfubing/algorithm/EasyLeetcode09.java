package com.dingfubing.algorithm;

public class EasyLeetcode09 {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = 0;
        int num = x;
        while (num != 0) {
            temp = temp * 10 + num %10;
            num = num / 10;
        }
        return temp == x;
    }
}

