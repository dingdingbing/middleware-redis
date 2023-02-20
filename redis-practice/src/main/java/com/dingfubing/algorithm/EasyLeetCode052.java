package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 14:22
 */
public class EasyLeetCode052 {

    public String longestPalindrome(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int maxStart = 0;
        int maxEnd = 0;
        boolean[][] res = new boolean[length][length];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j] && (i - j <= 2 || res[j + 1][i - 1])) {
                    res[i][j] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        maxStart = j;
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
