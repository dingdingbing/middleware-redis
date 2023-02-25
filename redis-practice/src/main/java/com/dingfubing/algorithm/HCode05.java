package com.dingfubing.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/22 19:35
 */
public class HCode05 {

    public static void main(String[] args) {
        System.out.println(Math.sqrt(2));
    }

    public void minSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        ArrayList<Integer> integers = new ArrayList<>();

        // 求因子
        for (int i = 2; i <= Math.sqrt(sum); i++) {
            if (sum % i == 0) {
                integers.add(i);
                // 剔除重复的值
                if (sum / i != i) {
                    integers.add(sum / i);
                }
            }
        }

        // 排序之后，left + right
        Arrays.sort(nums);
        for (int i = 0; i < integers.size(); i++) {
            int y = integers.get(i);
            int left = 0, right = nums.length - 1;
//            while (left < right) {
//                while (left < right && )
//                if (nums[right] + nums[left] > y) {
//                    if (nums[right] != y) {
//                        break;
//                    } else {
//                        right--;
//                    }
//                }
//            }
        }

    }

}
