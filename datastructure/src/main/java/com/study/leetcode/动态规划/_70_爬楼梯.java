package com.study.leetcode.动态规划;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _70_爬楼梯 {
    public static int climbStairs(int n) {

        if (n <= 1) {
            return 1;
        }
        if (n <= 2) {
            return 2;
        }
        /**
         *  如果只上一个台阶 只有一种走法
         *  如果是两个台阶，则有两种走法。
         *  假设上到第n个台阶，因为一次只能有一步或者一次只能走两步，则第n个台阶的走法是，n-1台阶走法的数量 加上 n-2走法的数量
         *  状态转移方程
         *  f（n） = f(n-1) + f(n-2);
         *
         */
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }
}
