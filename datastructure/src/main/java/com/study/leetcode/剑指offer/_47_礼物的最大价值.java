package com.study.leetcode.剑指offer;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *  
 * <p>
 * 提示：
 * <p>
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _47_礼物的最大价值 {


    public int maxValue(int[][] grid) {

        /**
         * dp[i][j]代表，在横坐标为i 和纵坐标为j的位置拿到的最大价值
         */
        int[][] dp = new int[grid.length + 1][grid[0].length + 1];

        /**
         *  dp[i][j]的值，只能从dp[i-1][j] 和dp[i][j-1]获取，选取两个最大的值，并加上grid[i-1][j-1]的价值
         *  就获取到dp[i][j]当前位置的最大值
         */
        for (int i = 1; i < grid.length + 1; i++) {
            for (int j = 1; j < grid[0].length + 1; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i - 1][j - 1];
            }
        }
        return dp[grid.length][grid[0].length];
    }

}
