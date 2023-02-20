package com.dingfubing.algorithm;

import jdk.nashorn.internal.ir.ReturnNode;

public class EasyLeetcode04 {
    public static void main(String[] args) {
        myAtoi("-2147483649");
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int index1 = 0, index2 = 0;
        // 这里是index
        int left = 0;
        int right = 0;
        for (int i = 0; i <= length / 2; i++) {
            left = right;
            if (nums1[index1] > nums2[index2]) {
                right = nums2[index2++];
            } else {
                right = nums1[index1++];
            }
        }
        if (length % 2 == 0) {
            return (right + left) / 2.0;
        }
        return right;
    }

    public static int myAtoi(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        int flag = 1;
        int res = 0;
        int start = i;
        for (; i < s.length(); i++) {
            if (i == start && s.charAt(i) == '-') {
                flag = -1;
            } else if (i == start && s.charAt(i) == '+') {
                flag = 1;
            } else if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';

                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    return Integer.MAX_VALUE;
                }
                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                    return Integer.MIN_VALUE / 10;
                }
                res = res * 10 + num * flag;
            } else {
                break;
            }
        }
        return res;
    }
}
class Solution {
    public int myAtoi(String s) {
        int sign = 1;
        int res = 0;
        int m = s.length();
        int i = 0;
        while(i < m && s.charAt(i)==' '){
            i++;
        }
        int start = i;
        for(; i < m; i++){
            char c = s.charAt(i);
            if(i==start && c=='+'){
                sign = 1;
            }else if(i==start && c=='-'){
                sign = -1;
            }else if(Character.isDigit(c)){
                int num = c-'0';
                if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10&&num>Integer.MAX_VALUE%10)){
                    return Integer.MAX_VALUE;
                }

                if(res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10&&-num<Integer.MIN_VALUE%10)){
                    return Integer.MIN_VALUE;
                }
                res = res*10+sign*num;
            }else{
                break;
            }
        }
        return res;
    }
}