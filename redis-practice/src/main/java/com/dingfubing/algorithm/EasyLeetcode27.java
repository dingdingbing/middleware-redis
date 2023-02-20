package com.dingfubing.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EasyLeetcode27 {

    public static void main(String[] args) {
        method1(new ArrayList<>(1));
        method2(new ArrayList<>(2));
    }



    public static void method1(List<Integer> integers) {

        System.out.println("1");

    }

    public static void method2(List<String> strings) {
        System.out.println("2");
    }

    public static int removeElement(int[] nums, int val) {
        // i 代表等于val的值，需要替换的值
        // j 代表可以替换的值\
        int i = 0, j = 1;
        for (; j < nums.length; ) {
            if (nums[i] == val) {
                if (nums[j] != val) {
                    // 替换位置
                    nums[i] = nums[j];
                } else {
                    j++;
                }
            } else {
                i++;
            }
        }
        return i + 1;
    }
}
