package com.study.leetcode.字符串;

/**
 * @ClassName _151_翻转字符串里的单词
 * @Description
 * @Author za-yaowei
 * @Date 2021/3/2 18:05
 * @Version 1.0
 *
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 说明：
 *
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 示例 1：
 *
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 *
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 *
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 *
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _151_翻转字符串里的单词 {

    /**
     *  1、将字符串的多余空格删除
     *  2、然后将字符串进行翻转
     *  3、再将每个单词进行翻转
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() ==0) return "";

        char[] chars = s.toCharArray();
        // 删除字符串多余的空格
        // 字符串真实的长度。
        int len = 0;
        // 当前字符串的指针
        int cur = 0;
        // 前一个节点是否是空格
        boolean prevSpace = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' '){
                chars[cur++] = chars[i];
                prevSpace = false;
            }else if (prevSpace == false){
                chars[cur++] = chars[i];
                prevSpace = true;
            }
        }
        // 字符串的真实长度等于当前字符串的指针长度
        len = prevSpace ? cur -1 : cur;
        if (len <= 0 ) return "";

        // 翻转字符串
        revers(chars,0,len);

        // 把字符串里的单词 进行翻转
        int prevIndex = -1;
        for (int i = 0; i <len; i++) {
            if (chars[i] == ' ') {
                revers(chars,prevIndex + 1 , i);
                prevIndex = i;
            }
        }
        // 翻转最后一个单词
        revers(chars,prevIndex + 1,len);


        return new String(chars,0,len);
    }

    /**
     *  翻转字符串
     * @param chars
     * @param li  开始位置
     * @param ri  结束位置
     */
    private void revers(char[] chars, int li, int ri) {
        ri--;
        while (li < ri){
            char temp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = temp;
            li++;
            ri--;
        }
    }
}
