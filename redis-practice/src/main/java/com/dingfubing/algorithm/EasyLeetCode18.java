package com.dingfubing.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/21 17:56
 */
public class EasyLeetCode18 {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296));;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum =(long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                        while (left < right && nums[right] == nums[right+1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                        while (left < right && nums[left] == nums[left-1]) {
                            left++;
                        }
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        right--;
                        left++;
                        while (left < right && nums[right] == nums[right+1]) {
                            right--;
                        }
                        while (left < right && nums[left] == nums[left-1]) {
                            left++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
