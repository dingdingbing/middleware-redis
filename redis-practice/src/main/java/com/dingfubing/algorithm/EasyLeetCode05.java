package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 11:18
 */
public class EasyLeetCode05 {

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int left, right, maxLength = 0, start = 0, len = 1;
        for (int i = 0; i < chars.length; i++) {
            left = i - 1;
            right = i + 1;
            // 相同字符
            while (left >= 0 && chars[left] == chars[i]) {
                left--;
                maxLength++;
            }
            // 相同字符
            while (right < s.length() && chars[right] == chars[i]) {
                right++;
                maxLength++;
            }
            while (left >= 0 && right < s.length() && chars[right] == chars[left]) {
                left--;
                right++;
                maxLength +=2;
            }
            if (maxLength > len) {
                len = maxLength;
                start = left;
            }
            maxLength = 1;
        }
        return s.substring(start, start + maxLength);
    }
}
