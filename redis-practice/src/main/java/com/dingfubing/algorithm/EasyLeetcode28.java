package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/21 13:31
 */
public class EasyLeetcode28 {

    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad", "sad"));
    }

    public static int strStr(String haystack, String needle) {
        // 双指针
        if (needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int temp = i;
            int j = 0;
            while (temp < haystack.length() && j < needle.length() && haystack.charAt(temp) == needle.charAt(j)) {
                temp++;j++;
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

}
