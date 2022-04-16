package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/15 16:26
 */
public class EasyLeetcode14 {

    public static void main(String[] args) {
        String[] strings = new String[]{"cir","car"};
        System.out.println(longestCommonPrefix(strings));
    }

    public static String longestCommonPrefix(String[] strs) {
        int length = strs.length;
        if (length<2) {
            return strs[0];
        }
        int min = strs[0].length();
        String minStr = strs[0];
        for(int i = 1; i< length; i++) {
            if (min > strs[i].length()) {
                min = strs[i].length();
                minStr = strs[i];
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < minStr.length(); i++) {
            char temp = minStr.charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != temp) {
                    return minStr.substring(0, i);
                }
            }
            result.append(temp);
        }
        return result.toString();
    }

}
