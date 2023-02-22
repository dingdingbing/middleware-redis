package com.dingfubing.algorithm;

import java.util.Arrays;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/22 16:58
 */
public class HCode02 {

    public static void main(String[] args) {
        System.out.println(min(new int[]{3, 4}, new int[]{3, 2, 2, 1}));
    }

    public static int min(int[] cars, int[] people) {
        // 最大值
        int max = 0;
        int min = 0;
        for (int car : cars) {
            max = Math.max(car, max);
            min = Math.min(car, min);
        }

        // 排序
        Arrays.sort(people);
        // 双指针
        int i = 0, j = people.length - 1;
        int res1 = 0;
        int res2 = 0;

        while (i < j) {
            if (people[i] + people[j] > max) {
                j--;
                res1++;
            } else if (people[i] + people[j] == max) {
                i++;
                j--;
                res1++;
            } else {
                if (people[i] + people[j] > min) {
                    i++;
                    j--;
                    res1++;
                } else {
                    i++;
                    j--;
                    res2++;
                }
            }
        }
        // 可能人数是单数
        if (i == j) {
            res1++;
        }

        return res1 + res2;
    }
}
