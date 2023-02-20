package com.dingfubing.algorithm;

import java.util.HashMap;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/16 15:33
 */
public class EasyLeetCode01 {

    public static void main(String[] args) {

    }
    public int[] twoSum(int[] nums, int target) {
        // key -> num   v -> index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(target - num) != null) {
                return new int[]{};
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

}
