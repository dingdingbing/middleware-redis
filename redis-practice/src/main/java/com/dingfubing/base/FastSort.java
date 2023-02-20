package com.dingfubing.base;

/**
 * 快速排序
 * 使用分治法，分而治之，再用递归
 *
 * @author dingfubing
 * @since 2022/5/25 15:49
 */
public class FastSort {

    public static void main(String[] args) {
        FastSort fastSort = new FastSort();
        int[] arr = new int[]{2,2,2,2,3,4,5,7,2,1,2};
        fastSort.fastSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 定位中间数，将一个数组划分为两个的中间值
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] arr, int low, int high) {
        // 大于pivot 的放右边，小于的放左边
        int pivot = arr[high];
        // 思想是找到比pivot小的值，将他们进行互换
        int pointer = low;
        // 需要遍历数组,取的是最后一个值
        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                if (i != pointer) {
                    // 交换位置
                    int temp = arr[pointer];
                    arr[pointer] = arr[i];
                    arr[i] = temp;
                }
                pointer++;
            }
        }
        // 最后将pointer和pivot互换位置，并返回划分两个list的中间值
        arr[high] = arr[pointer];
        arr[pointer] = pivot;

        return pointer;

    }

    public int[] fastSort(int[] arr, int low, int high) {
        if (low < high) {
            // 先找到每段数组的中点
            int partition = partition(arr, low, high);
            fastSort(arr, low, partition - 1);
            fastSort(arr, partition + 1, high);
        }
        return arr;
    }

}
