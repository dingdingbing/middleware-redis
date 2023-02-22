package com.dingfubing.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/22 15:11
 */
public class HCode01 {

    public static void main(String[] args) {
        int[][] ranges = new int[5][3];
        ranges[0][0] = 2;
        ranges[0][1] = 4;
        ranges[0][2] = 1;

        ranges[1][0] = 6;
        ranges[1][1] = 9;
        ranges[1][2] = 2;

        ranges[2][0] = 0;
        ranges[2][1] = 5;
        ranges[2][2] = 1;

        ranges[3][0] = 3;
        ranges[3][1] = 7;
        ranges[3][2] = 3;

        System.out.println(minMeetingRooms(ranges));
    }

    // 二维数组 n行 3列
    public static int minMeetingRooms(int[][] ranges) {
        if (ranges.length == 0) {
            return 0;
        }
        // 最终结果
        int res = 0;
        // 临时变量，从queue为0的时候开始记录
        int tempRes = 0;
        // 根据开始时间排序
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        // 根据结束时间排序
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] range : ranges) {
            // 判断下一个queue里面的每个节点和当前的结束时间是否冲突
            while (!queue.isEmpty()) {
                // 去栈顶的元素
                Integer[] peek = queue.peek();
                // 结束时间小于开始时间，则删除
                if (peek[0] < range[0]) {
                    Integer[] poll = queue.poll();
                    tempRes -= poll[1];
                } else {
                    break;
                }
            }
            queue.offer(new Integer[]{range[1], range[2]});
            tempRes += range[2];
            res = Math.max(res, tempRes);
        }
        return res;
    }
}
