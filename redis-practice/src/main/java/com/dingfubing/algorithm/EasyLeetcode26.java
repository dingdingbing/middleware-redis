package com.dingfubing.algorithm;

/**
 * TODO 卡住了
 *
 * @author dingfubing
 * @since 2022/4/15 20:12
 */
public class EasyLeetcode26 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2,3,4,5,6,7,7};
        System.out.println(removeDuplicates2(nums));;
        System.out.println("--");
        for (int num : nums) {
            System.out.println(num);
        }
    }

    /**
     * 此种方法其实算是卡bug了，思想是先找出重复的，标记为最大值，因为最大值只可能在最后面
     * 找出重复的设置为最大值之后，遍历
     * 还是使用的双指针， 找到一个之前设置的最大的数，再往后找一个不是最大数的数，替换一下值，性能太低了
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int res = 0;
        for (int i = 0, j = 1; j < nums.length; ) {
            // 首先找出重复的数字，并标注成-1
            if (nums[i] == nums[j]) {
                nums[j] = Integer.MAX_VALUE;
                j++;
                res++;
            } else {
                i = j;
                j++;
            }
        }
        // 然后再交换位置 第一个数肯定不为-1
        out:
        for (int i = 1; i < nums.length; i++) {
            // 寻找不为 -1 的数进行替换
            if (nums[i] == Integer.MAX_VALUE) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != Integer.MAX_VALUE) {
                        nums[i] = nums[j];
                        nums[j] = Integer.MAX_VALUE;
                        continue out;
                    }
                }
            }

        }
        return nums.length - res;
    }

    public static int removeDuplicates2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[i+1] = nums[j];
                i++;
            }
            j++;
        }
        return i+1;
    }

}
