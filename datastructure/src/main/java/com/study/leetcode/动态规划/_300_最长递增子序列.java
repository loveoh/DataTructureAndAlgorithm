package com.study.leetcode.动态规划;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _300_最长递增子序列 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        // dp[i]是以nums[i]元素结尾的最长上升子序列的长度。
        // 设每一个状态初始值都是1
        int max = dp[0] = 1;
        /**
         * 状态转移方程：
         *  nums[i] 的最长上升子序列长度，需要遍历[0,i)的最大的子序列长度。并比较获取最大的子序列长度并+1；故  0<= j <i；
         *  1、nums[i] <= nums[j]; 跳过此次循环
         *  2、nums[i] > nums[j]; dp[i] = Math.max(dp[i],(dp[j] + 1));
         *  一轮循环结束后，dp[i]就是以nums[i]元素结尾的最大子序列的长度
         *  最终选取dp数组中的最大值，就是所求值
         */
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] <= nums[j]) {
                    continue;
                } else {
                  dp[i] = Math.max(dp[i],(dp[j] + 1));
                }
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }

}
