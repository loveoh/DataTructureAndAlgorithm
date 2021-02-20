package com.study.leetcode.分治;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _215_数组中的第K个最大元素 {


    /**
     * 使用小顶堆 解决top K 问题
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {

//        PriorityQueue queue = new PriorityQueue(k, new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return o1-o2;
//            }
//        });
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> a - b);
        for (int i = 0 ; i < nums.length; i++){
            if (i  < k){
                maxHeap.add(nums[i]);
            }else {
                int peek = maxHeap.peek();
                if (nums[i] > peek){
                    maxHeap.remove();
                    maxHeap.add(nums[i]);
                }
            }
        }

        return maxHeap.peek();
    }




    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {

        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest(nums,2));
    }
}
