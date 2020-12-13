package com.study.leetcode.链表;

/**
 * @ClassName 反转链表_206
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/2 16:06
 * @Version 1.0
 */
public class 反转链表_206 {

    /**
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */

    // 使用递归翻转链表
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next ==null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    // 通过迭代完成链表翻转
    public static  ListNode reverseList1(ListNode head) {
        if (head == null || head.next ==null){
            return head;
        }
        ListNode newHead = null;
        while (head !=null ){
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;

        System.out.println(reverseList1(head).val);
        System.out.println(head.val);

    }
}
