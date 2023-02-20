package com.dingfubing.algorithm;

import java.util.Arrays;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/20 13:52
 */
public class MiddleLeetcode567 {

    public static void main(String[] args) {
        System.out.println(checkInclusion("abc", "ccccbbbbaaaa"));
    }

    // s1 是 s2的子串
    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;

    }

}
