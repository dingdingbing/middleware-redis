package com.dingfubing.algorithm;

import com.sun.xml.internal.ws.encoding.MtomCodec;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/20 10:27
 */
public class EasyLeetCode042 {

    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k1 = (nums1.length + nums2.length + 1) / 2;
        int k2 = (nums1.length + nums2.length + 2) / 2;
        return (getK(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k1)
            + getK(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k2)) * 0.5;
    }

    public int getK(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (len2 == 0) {
            return nums1[start1 + k - 1];
        }
        // 循环退出条件
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        // num1 的 第 k/2 小的index
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            // 剔除num2 j 的前面的数
            return getK(nums1, start1, end1, nums2, j + 1, end2, (k - (j - start2 + 1)));
        } else {
            return getK(nums1, i + 1, end1, nums2, start2, end2, (k - (j - start2 + 1)));
        }


    }


}
