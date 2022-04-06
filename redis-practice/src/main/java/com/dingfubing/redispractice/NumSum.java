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

    // 链表之和
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(() -> System.out.println(1));
//        int i = maxProfit(a);
//        System.out.println(i);

        int[] a = {1, 2, 3, 4, 5};
        int[] c = {5, 4, 3, 2, 1};
        int[] b = {0, 0, 0, 0, 0};
        int[] pre = new int[5];
        int[] after = new int[5];
        int length = a.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                pre[i] = 1;
            } else if (i == 1) {
                pre[i] = a[0];
            } else if (i == 2) {
                pre[i] = a[1];
            } else {
                pre[i] = pre[i - 1] * a[i - 1];
            }
        }

        for (int i = 0; i < length; i++) {
            b[i] = pre[i] * after[i];
            System.out.println(b[i]);
        }

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
