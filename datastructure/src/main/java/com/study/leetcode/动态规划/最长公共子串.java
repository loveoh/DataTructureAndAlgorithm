package com.study.leetcode.动态规划;

import org.springframework.util.StringUtils;

/**
 * @ClassName 最长公共子串
 * @Description  子串是里连续的子序列
 * @Author za-yaowei
 * @Date 2021/2/27 17:07
 * @Version 1.0
 */
public class 最长公共子串 {

    /**
     *  求两个字符串的最长公共子串长度
     *  ABCBA 和 BABCA 的最长公共子串是ABC ，长度为3
     * @param str1
     * @param str2
     * @return
     */
    public int lcs(String str1,String str2){
        if (StringUtils.isEmpty(str1) ||StringUtils.isEmpty(str2)) return 0;

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int max = 0;
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i-1] == chars2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = 0;
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

}
