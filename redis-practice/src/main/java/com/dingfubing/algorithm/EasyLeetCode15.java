package com.dingfubing.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/21 14:08
 */
public class EasyLeetCode15 {

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0});
        System.out.println(lists);
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i +1;
            int k = nums.length-1;
            if (nums[i] > 0) {
                break;
            }
            // 剔除重复的数据
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            while (j < k) {
                if (nums[i] + nums[j] +nums[k] < 0) {
                    j++;
                    while (j < k && nums[j] == nums[j-1]) {
                        j++;
                    }
                } else if (nums[i] + nums[j] +nums[k] > 0) {
                    k--;
                    while (j < k && nums[k] == nums[k+1]) {
                        k--;
                    }
                } else {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[j]);
                    integers.add(nums[k]);
                    res.add(integers);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j-1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k+1]) {
                        k--;
                    }
                }
            }
        }

        return res;
    }
}
