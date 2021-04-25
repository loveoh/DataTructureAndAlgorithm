package com.study.leetcode.字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName _3_无重复字符的最长子串
 * @Description
 * @Author za-yaowei
 * @Date 2021/3/3 10:56
 * @Version 1.0
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _3_无重复字符的最长子串 {

    /**
     *  滑动窗口解法
     *  假设字符串为abcdefscdesac;假设一个left指针，初始化为left=0；从左开始遍历字符串。
     *  假如遍历到第i个字符，和前面的字符出现重复，left = Math.max(chars[i] + 1, left) 则以chars[i]结尾的无重复子串为 i -left + 1;
     *  遍历结束之后，去除最大的重复子串即可
     * @param s
     * @return
     */

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        int prevIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            // 该字符重复的
            if (map.containsKey(chars[i])) {
                prevIndex = map.get(chars[i]) + 1 ;
                left = Math.max(prevIndex, left);
            }
            map.put(chars[i], i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcd"));
    }
}
