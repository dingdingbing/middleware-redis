package com.dingfubing.algorithm;

public class EasyLeetcode11 {
    public int maxArea(int[] height) {
        int i =0, j = height.length - 1;
        int max = 0;
        while (i < j) {
            max = height[i] > height[j] ? Math.max((j-i) * height[j--], max) :Math.max ((j-i) * height[i++], max);
        }
        return max;
    }
}
