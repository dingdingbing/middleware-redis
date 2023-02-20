package com.dingfubing.algorithm;

import java.util.Arrays;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/19 16:41
 */
public class MiddleLeetcode3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aa"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        // value 是索引
        int[] arr = new int[128];
        Arrays.fill(arr, -1);
        char[] chars = s.toCharArray();
        int maxLen = 0;
        for (int i = 0, j = 0; j < chars.length; j++) {
            char current = chars[j];
            // 重复了
            if (arr[current] != -1) {
                // 应该从重复位置 + 1 的位置往后继续查找
                // 但是为了保证慢指针 i只能往右移动，所以这里取最大值
                i = Math.max(arr[current] + 1, i);
            }
            // 不管重复不重复，都往arr里面塞值
            arr[current] = j;
            maxLen = Math.max(maxLen, j - i + 1);

        }
        return maxLen;
    }
}
