package com.dingfubing.redispractice;

import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.jar.JarEntry;

public class NumSum {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // 链表之和
    public static void main(String[] args) {
        String[] strings = new String[]{"cir","car"};
        // System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));

        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
            , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
            , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
            , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
            , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
            , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
            , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
            , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
            , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
    }


    public static int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        // 双指针法
        while (left < right) {
            int width = right - left;
            maxArea = Math.max(maxArea, width * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // public boolean isValidSudoku(char[][] board) {
    //     // 校验存在与否可以使用数组操作，下标代表数，a[i] 的值为0则代表存在
    //     // 校验横的数字
    //     int[][] colArr = new int[9][9];
    //     // 校验竖的数字
    //     int[][] rowArr = new int[9][9];
    //     // 校验九宫格的数字
    //     int[][] circleArr = new int[9][9];
    //
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[i].length; j++) {
    //             if (board[i][j] == '.') {
    //                 continue;
    //             }
    //             // (char)'9' - (char)'0' = 9 (int)
    //             int val = board[i][j] - '0';
    //             if (colArr[i][val] == 1) {
    //                 return false;
    //             }
    //             if (rowArr[j][val] == 1) {
    //                 return false;
    //             }
    //             if (circleArr[i][val] == 1) {
    //                 return false;
    //             }
    //             colArr[i][val] = 1;
    //             rowArr[j][val] = 1;
    //             // 9 -> heng = 2 shu = 2 a[2][2] = 9
    //             int heng = (val-1) / 3;
    //             int shu = val - 1 - 3 * heng;
    //             // 如果board[4][6]=9
    //             circleArr[i / 3 * 3 + val / 3][j / 3 * 3 + val % 3] = 1;
    //         }
    //     }
    // }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        // 1.先排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int sum = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < sum) {
                    left++;
                } else if (nums[left] + nums[right] > sum) {
                    right--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                }
            }
        }

        return res;
    }

    public static int reverse(int x) {
        if (x == 0) {
            return x;
        }
        boolean flag = x >= 0;
        StringBuilder a = new StringBuilder();
        long temp = flag ? x : -(long) x;
        while (temp > 0) {
            a.append(temp % 10);
            temp = temp / 10;
        }

        String result = (flag ? "" : "-") + a;

        if (Long.parseLong(result) > Integer.MAX_VALUE || Long.parseLong(result) < Integer.MIN_VALUE) {
            return 0;
        } else {
            return Integer.parseInt(result);
        }

    }


    public static String convert(String s, int numRows) {
        int length = s.length();
        if (length < 3 || length <= numRows) {
            return s;
        }
        char[] chars = s.toCharArray();

        // 一组数的大小 2 * numRows -1  -1
        // a    |i
        // b   h|
        // c  g |
        // d f  |
        // e    |
        int t = 2 * numRows - 2;
        int cols = (length / t + 1) * (numRows - 1);
        char[][] arr = new char[numRows][cols];
        // 竖着赋值
        int r = 0;
        int c = 0;
        for (int i = 0; i < length; i++) {
            arr[r][c] = chars[i];
            // 一个t为一组，大于等于 numRows-1的则
            if (i % t < numRows - 1) {
                r++;
            } else {
                r--;
                c++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] value : arr) {
            for (char item : value) {
                if (item != 0) {
                    stringBuilder.append(item);
                }
            }
        }
        // 横着取值
        return stringBuilder.toString();
    }

    public static final int size = 10005;

    public int maxScoreSightseeingPair(int[] values) {
        int length = values.length;
        // 景点+分数
        int[] score = new int[size];
        int[] prevMax = new int[size];
        for (int i = 0; i < length - 1; i++) {
            score[i] = values[i] + i;
        }
        // 返回的数组表示的是前i个集合中最大值
        max(prevMax, score);

        int maxScore = -1;
        // 因为 i < j 所以j可以从1 开始
        for (int j = 1; j < length - 1; j++) {
            maxScore = Math.max(maxScore, prevMax[j - 1] + values[j] - j);
        }

        return maxScore;
    }


    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int[] lowest = new int[size];
        lowestPrice(lowest, prices);

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - lowest[i - 1]);
        }
        return maxProfit;
    }

    public static void lowestPrice(int[] lowest, int[] prices) {
        int length = prices.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                lowest[i] = prices[i];
            } else {
                lowest[i] = Math.min(prices[i], lowest[i - 1]);
            }
        }
    }

    public void max(int[] max, int[] score) {
        int length = score.length;
        for (int i = 0; i < length - 1; i++) {
            if (i == 0) {
                max[i] = score[i];
            } else {
                max[i] = Math.max(score[i], max[i - 1]);
            }
        }
    }


    public static String longest(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int maxLen = 0;
        int begin = 0;
        for (int i = 0; i < length - 2; i++) {
            int oddNum = getInt(chars, i, i);
            int plural = getInt(chars, i, i + 1);
            int max = Math.max(oddNum, plural);
            if (max > maxLen) {
                maxLen = max;
                begin = i - (maxLen - 1) / 2;
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public static int getInt(char[] chars, int i, int j) {

        while (i >= 0 && j <= chars.length - 1) {
            if (chars[i] == chars[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }

        return j - i - 1;
    }

    public static ListNode sum(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        // 进位
        int carry = 0;

        ListNode p = result;
        while (!(l1 == null && l2 == null)) {

            int v1 = l1 == null ? 0 : l1.value;
            int v2 = l2 == null ? 0 : l2.value;

            p.next = new ListNode((v1 + v2 + carry) % 10);

            carry = (v1 + v2 + carry) / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            p = p.next;

        }

        // 循环结束，如果carry != 0;
        if (carry != 0) {
            ListNode listNode = new ListNode();
            listNode.value = carry;
            listNode.next = null;
            result.next = listNode;
        }

        return result.next;
    }

    class LRUHashMap extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUHashMap(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        @Override
        public Integer get(Object key) {
            return super.getOrDefault(key, -1);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return super.removeEldestEntry(eldest);
        }
    }


}

class ListNode {

    public ListNode next;
    public int value;

    public ListNode() {
    }

    public ListNode(int value) {
        this.value = value;
    }
}
