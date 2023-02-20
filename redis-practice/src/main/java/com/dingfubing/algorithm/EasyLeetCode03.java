package com.dingfubing.algorithm;

import java.util.Arrays;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/16 16:07
 */
public class EasyLeetCode03 {

    public static void main(String[] args) {

    }
    public static int lengthOfLongestSubstring(String s) {
        int[] arr = new int[128];
        Arrays.fill(arr, -1);
        int maxL = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            // 重复了
            if (arr[s.charAt(i)] != -1) {
                j = Math.max(arr[s.charAt(i)] + 1, j);
            }
            arr[s.charAt(i)] = i;
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }
}
