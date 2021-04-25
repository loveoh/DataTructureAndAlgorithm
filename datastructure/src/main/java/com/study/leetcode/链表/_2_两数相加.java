package com.study.leetcode.链表;

import java.util.List;

/**
 * @ClassName _2_两数相加
 * @Description
 * @Author za-yaowei
 * @Date 2021/4/12 13:03
 *
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _2_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       //设置一个虚拟头结点
        ListNode pre = new ListNode();
        // 当前节点的指针指向前驱结点
        ListNode cur = pre;
        int num = 0;// 表示进位
        while (l1 != null || l2 != null){
            int x = l1 == null ? 0 :l1.val;
            int y = l2 == null ? 0 : l2.val;
            int res = x + y + num;
            num = res / 10;
            cur.next = new ListNode(res % 10);
            cur = cur.next;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (num != 0){
            cur.next = new ListNode(num);
        }
        return pre.next;
    }
}
