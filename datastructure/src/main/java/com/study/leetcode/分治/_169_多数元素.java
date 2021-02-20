package com.study.leetcode.分治;

import java.util.Arrays;
import java.util.Collections;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class _169_多数元素 {

    /**
     *  摩尔投票法
     *  设置两个变量，value为数组中的众数，count为该数字出现的次数
     *  假设数组中的众数为第一个元素，  此时count=0；
     *  遍历数组，先判断count是否为0；如果count=0；将当前元素赋值给value；
     *  判断value 是否等于 nums[i]; 相等的话count++；如果不相等count-- ；
     *  如果存在众数的话，count最终肯定>0；此时value就是数组中的众数
     *
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        int count = 0;
        int value = nums[0];
        for (int num : nums){
            if (count == 0){
                value = num;
            }
            if (value == num){
                count++;
            }else {
                count--;
            }

        }
        return value;

    }

    /**
     * 分治算法
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        return majorityEle(nums, 0, nums.length);
    }

    private static int majorityEle(int[] nums, int begin, int end) {

        if (end - begin < 2) {
            return nums[begin];
        }

        int mid = (begin + end) >> 1;
        int left = majorityEle(nums, begin, mid);
        int right = majorityEle(nums, mid, end);
        if (left == right){
            return left;
        }

        /**
         * 在当前数组范围内。看该数字在当前数组范围的个数
         */
        int leftNum = buildNum(nums, left, begin, end);
        int rightNum = buildNum(nums, right, begin, end);

        return leftNum > rightNum ? left : right;
    }

    private  static int buildNum(int[] nums, int left, int begin, int mid) {
        int count  = 0;
        for (int i = begin; i < mid ; i++) {
            if (nums[i] == left){
                count++;
            }
        }
        return count;
    }


    /**
     * 排序之后，在数组最中间的位置 就是多元素
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,23,3,2,2,2,2,2,2};
        System.out.println(majorityElement(nums));
    }
}
