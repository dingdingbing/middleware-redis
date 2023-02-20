package com.dingfubing.algorithm;

import java.security.spec.MGF1ParameterSpec;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/17 9:37
 */
public class EasyLeetCode041 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // index
        int length = (nums1.length + nums2.length);
        int index1 = 0, index2 = 0;
        int left = 0, right = 0;
        for (int i = 0; i <= length / 2; i++) {
            left = right;
            // 长度不一致，可能会越界
            if (index2 < nums2.length && (index1 >= nums1.length || nums1[index1] > nums2[index2]) ) {
                right = nums2[index2++];
            } else {
                right = nums1[index1++];
            }
        }
        if ((length & 1) == 0) {
            return (right + left)/2.0 ;
        }
        return right;

    }
}
