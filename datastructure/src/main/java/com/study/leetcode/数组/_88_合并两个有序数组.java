package com.study.leetcode.数组;

/**
 * @ClassName _88_合并两个有序数组
 * @Description
 * @Author za-yaowei
 * @Date 2021/3/3 17:56
 * @Version 1.0
 */
public class _88_合并两个有序数组 {


    /**
     * 双指针算法， 从后往前遍历
     * nums1 数组的后半部分是空数组，两个数组都从后往前遍历，然后将大的值放到后面
     *  时间复杂度 O(m + n)
     *  空间复杂度 O（1）
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {


        int l1 = m-1;
        int l2 = n-1;
        int i = m + n - 1;
        while (l1 >= 0 && l2 >= 0) {

            nums1[i--] = nums1[l1] > nums2[l2] ? nums1[l1--] : nums2[l2--];
        }

//        while (l1 >= 0 ){
//            nums1[i--] = nums1[l1--];
//        }

        while (l2 >= 0 ){
            nums1[i--] = nums2[l2--];
        }
    }

    /**
     * 双指针算法，
     *  时间复杂度 O(m + n)
     *  空间复杂度 O（m） 需要把num1数组先复制出来临时存储，需要用到O（m）的空间
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 先把num1 数组copy出来，存放在num1copy
        int[] num1Copy = new int[m];
        System.arraycopy(nums1, 0, num1Copy, 0, m);

        int l1 = 0;
        int l2 = 0;
        int i = 0;
        while (l1 < m && l2 < n) {
            nums1[i++] = num1Copy[l1] < nums2[l2] ? num1Copy[l1++] : nums2[l2++];
        }

        while (l1 < m ){
            nums1[i++] = num1Copy[l1++];
        }

        while (l2 < n ){
            nums1[i++] = nums2[l2++];
        }
    }
}
