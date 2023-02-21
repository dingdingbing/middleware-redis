package com.dingfubing.algorithm;

import java.util.Arrays;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/21 15:38
 */
public class EasyLeetCode16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(target - sum) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    k--;
                }  else {
                    j++;
                }
            }
        }
        return res;
    }
}
