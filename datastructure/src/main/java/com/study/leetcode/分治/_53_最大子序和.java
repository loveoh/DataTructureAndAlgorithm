package com.study.leetcode.分治;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _53_最大子序和 {


    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * 分治算法求解
     *  求出 [begin，end) 中的最大子序列和，首先找到中间索引，mid =（begin + end） / 2;
     *  1 、最大值在begin -> mid 之间，此时只需要求出 [begin,mid) 中间的最大值
     *  2、 最大值在 mid -> end之间，此时只需要求出 [mid ，end) 中间的最大值
     *  3、 最大值横跨在分开后的两个数组之间的，从mid开始，向左求出最大的连续子序列的值leftMax，
     *      从mid开始，向右求出最大的连续子序列的值rightMax。两者相加求出max、
     *   然后从max，leftMax，rightMax 三个数中求出最大的值。以此来递归求出整个数组的最大的值。
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    private static int maxSubArray(int[] nums, int begin, int end) {

        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;
        int leftMax = nums[mid - 1];
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }
        int rightMax = nums[mid];
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }
        int max = rightMax + leftMax;

        return Math.max(max, (Math.max(maxSubArray(nums, begin, mid), maxSubArray(nums, mid, end))));
    }


    public static void main(String[] args) {
//       int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {3, -7, 9, -2};

        System.out.println(maxSubArray(nums));
    }
}
