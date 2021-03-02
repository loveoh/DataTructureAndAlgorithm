package com.study.leetcode.动态规划;

/**
 * @ClassName 背包问题
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/28 21:23
 * @Version 1.0
 */
public class 背包问题 {


    /**
     * 状态压缩，可以使用一维数组
     *
     * @param weights  物品的重量
     * @param values   物品的价值
     * @param capacity 背包的容量
     * @return
     */
    public static int selectPage(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || capacity <= 0) return 0;
        /**
         *  dp[i][j] 代表最大承重为j，并且有i个物品可选的情况下，背包的最大总价值
         */
//        int[][] dp = new int[values.length + 1][capacity + 1];
        int[] dp = new int[capacity + 1];
        /**
         *  使用二维数组，记录每一种情况。
         *  重量和价值具有相关性，可以作为同一个物体看待。作为纵坐标
         *  横坐标可以看做是背包的重量。
         *
         *  由于dp[i][j]的结果，只需要用到dp[i-1][j]里面的结果。
         *  所以dp数组，可以从二维数组优化到一维数组。
         */
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {

                /**
                 *  当前物品可以选择选取，也可以选择不选取。
                 *  如果选了，价值为dp[i - 1][j - weights[i - 1]] + values[i - 1]
                 *  如果不选，价值为dp[i - 1][j]
                 *  选两者最大的。
                 */
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);

            }
        }
        return dp[capacity];
    }

    /**
     * @param weights  物品的重量
     * @param values   物品的价值
     * @param capacity 背包的容量
     * @return
     */
    public static int selectPage1(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || capacity <= 0) return 0;
        /**
         *  dp[i][j] 代表最大承重为j，并且有i个物品可选的情况下，背包的最大总价值
         */
        int[][] dp = new int[values.length + 1][capacity + 1];

        /**
         *  使用二维数组，记录每一种情况。
         *  重量和价值具有相关性，可以作为同一个物体看待。作为纵坐标
         *  横坐标可以看做是背包的重量。
         */
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    // 背包容量不能装下当前物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    /**
                     *  当前物品可以选择选取，也可以选择不选取。
                     *  如果选了，价值为dp[i - 1][j - weights[i - 1]] + values[i - 1]
                     *  如果不选，价值为dp[i - 1][j]
                     *  选两者最大的。
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[values.length][capacity];
    }

    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        System.out.println(selectPage(weights, values, 10));
    }

}
