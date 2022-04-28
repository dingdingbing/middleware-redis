package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/22 16:17
 */
public class EasyLeetcode66 {

    public static void main(String[] args) {

    }

    public int[] plusOne(int[] digits) {

        // 倒着循环
        for (int i = digits.length - 1; i > 0; i--) {

            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
