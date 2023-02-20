package com.dingfubing.algorithm;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 16:32
 */
public class EasyLeetCode07 {

    public static void main(String[] args) {
        int reverse = reverse(1534236469);

    }

    public  static  int reverse(int x) {
        int flag = x >= 0 ? 1 : -1;
        x = Math.abs(x);
        int res = 0;
        boolean need = x > Integer.MAX_VALUE / 10;
        while (x > 0) {
            res = res * 10 + x % 10;
            x = x / 10;
            if (need) {
                if (flag == 1) {
                    if (res > Integer.MAX_VALUE / 10 && x > 0) {
                        return 0;
                    }
                } else {
                    if (-res < Integer.MIN_VALUE / 10 && x > 0) {
                        return 0;
                    }
                }
            }
        }
        return flag * res;
    }
}
