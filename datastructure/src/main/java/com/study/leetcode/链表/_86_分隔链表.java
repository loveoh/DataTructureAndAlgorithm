package com.study.leetcode.链表;

/**
 * @ClassName _86_分隔链表
 * @Description
 * @Author za-yaowei
 * @Date 2021/4/19 13:02
 * @Version 1.0
 */
public class _86_分隔链表 {
    /**
     *  定义两个临时链表。分别存在大的节点，和小节点
     *  之后再将两个链表拼接起来
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        ListNode head1 = new ListNode();
        ListNode head2 = new ListNode();

        ListNode cur1 = head1;
        ListNode cur2 = head2;

        while (head != null){
            if (head.val < x ){
                cur1.next = head;
                cur1 = cur1.next;
            }else {
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = head.next;
        }
        // 将大的那一半的链表的最后一个节点的next指针置为null
        // 否则会出现链表有环的情况
        cur2.next = null;
        cur1.next = head2.next;
        return head1.next;
    }

}
