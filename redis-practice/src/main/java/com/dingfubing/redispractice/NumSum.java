package com.dingfubing.redispractice;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NumSum {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static int[] constructArr(int[] a) {
        if (a.length == 0) {
            return new int[]{};
        }
        if (a.length == 1) {
            return new int[]{a[0]};
        }
        int[] b = new int[a.length];
        b[0] = 1;
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }

        int temp = 1; // 5
        for (int i = a.length - 2; i >= 0; i--) {
            temp = a[i + 1] * temp;
            b[i] = b[i] * temp;
        }
        return b;
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


    public static String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }

        int maxLength = 0;
        int begin = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 可能存在单字回文 和双字回文
            int single = calc(chars, i, i);
            int dou = calc(chars, i, i + 1);
            int max = Math.max(single, dou);
            if (max > maxLength) {
                maxLength = max;
                begin = i - (max - 1) / 2;
            }
        }

        return s.substring(begin, begin + maxLength);
    }

    // 返回长度
    public static int calc(char[] a, int i, int j) {
        int length = a.length;
        while (i >= 0 && j < length) {
            if (a[i] == a[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }
        return j - i - 1;
    }


    public static void main(String[] args) {
        String s = "abcdefghijklmn";
        String convert = convert(s, 4);
        System.out.println(convert);
    }

    public static String convert(String s, int numRows) {
        int length = s.length();
        if (length < numRows) {
            return s;
        }
        char[] chars = s.toCharArray();
        int j = length / (2 * numRows - 2) * (numRows - 1) + (length % (2 * numRows - 2) < numRows ? 0 : length % (2 * numRows - 2) - numRows) + 1;
        int[][] result = new int[numRows][j];

        int tempI = 0;
        int tempJ = 0;

        for (int i = 0; i < length; i++) {
            result[tempI][tempJ] = chars[i];
            if (i % (2 * numRows - 2) < numRows) {
                tempI++;

            } else {
                tempI--;
                tempJ++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] ints : result) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    stringBuilder.append(anInt);
                }
            }
        }
        return stringBuilder.toString();
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
