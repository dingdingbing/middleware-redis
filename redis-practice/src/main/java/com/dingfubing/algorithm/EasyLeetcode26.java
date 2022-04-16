package com.dingfubing.algorithm;

/**
 * TODO 卡住了
 *
 * @author dingfubing
 * @since 2022/4/15 20:12
 */
public class EasyLeetcode26 {

    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int res = 0;
        for (int i = 0, j = 1; i < nums.length && j < nums.length; j++) {
            if (nums[i] == nums[j]) {
                nums[j] = -1;
            } else {
                i++;
                if (i < j && i < nums.length - 1 && nums[i] == -1) {
                    nums[i] = nums[j];
                    nums[j] = -1;
                }
                res++;
            }
        }
        return res;
    }

}
