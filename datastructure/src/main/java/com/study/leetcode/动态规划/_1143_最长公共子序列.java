package com.study.leetcode.动态规划;

import org.springframework.util.StringUtils;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *  
 *
 * 提示:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1143_最长公共子序列 {


    /**
     * 状态压缩，将二维数组压缩成为一维数组
     * @param text1
     * @param text2
     * @return
     */
    public  static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null){
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        /**
         * 定义状态
         * dp[i][j] 代表 char1[i] 和char2[j] 为结尾的最长公共子序列
         *
         */

//        int[][] dp = new int[chars1.length + 1][chars2.length +1];
        int[] dp = new int[chars2.length + 1];
        /**
         * 状态转移方程：
         *   chars1[i] = chars2[j]; 以chars[i-1]和chars2[j-1]结尾的最长公共子序 +1
         *   chars1[i] != chars2[j]; 选择以chars[i-1]或者chars2[j-1]结尾的最长公共子序中的最大值
         */
        // 选择从1开始，相当于从0位置的字符数组开始比较。
        // 可以规避 0位置的特殊情况。
        for (int i = 1; i <= chars1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= chars2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (chars1[i -1] == chars2[j - 1]){
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j - 1],dp[j]);
                }
            }
        }
        return dp[chars2.length];
    }


    public  static int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null){
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        /**
         * 定义状态
         * dp[i][j] 代表 char1[i] 和char2[j] 为结尾的最长公共子序列
         *
         */

        int[][] dp = new int[chars1.length + 1][chars2.length +1];

        /**
         * 状态转移方程：
         *   chars1[i] = chars2[j]; 以chars[i-1]和chars2[j-1]结尾的最长公共子序 +1
         *   chars1[i] != chars2[j]; 选择以chars[i-1]或者chars2[j-1]结尾的最长公共子序中的最大值
         */
        // 选择从1开始，相当于从0位置的字符数组开始比较。
        // 可以规避 0位置的特殊情况。
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i -1] == chars2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j - 1],dp[i-1][j]);
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";
        System.out.println(longestCommonSubsequence(str1,str2));
    }
}
