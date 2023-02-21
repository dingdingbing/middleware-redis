package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 17:12
 */
public class EasyLeetCode08 {

    public int myAtoi(String s) {
        int i = 0;
        while (s.charAt(i) == ' ') {
            i++;
        }
        int flag = 1;
        if (s.charAt(0) == '-') {
            flag = -1;
            i++;
        } else if (s.charAt(0) == '+') {
            i++;
        }
        StringBuilder numStr = new StringBuilder();
        while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            numStr.append(s.charAt(i));
            i++;
        }
        try {
            return Integer.parseInt(numStr.toString()) * flag;
        } catch (Exception e) {
            return 0;
        }
    }
}
