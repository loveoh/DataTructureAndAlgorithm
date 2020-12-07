package com.study.leetcode.链表;

import java.util.List;

/**
 * @ClassName 移除链表元素_203
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/2 18:32
 * @Version 1.0
 */
public class 移除链表元素_203 {

    /**
     * 删除链表中等于给定值 val 的所有节点。
     *
     * 示例:
     *
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {

        if (head == null)
            return null;
        if (head.val == val) {
            head.next = removeElements(head.next, val);
            return head.next;
        } else {
            head.next = removeElements(head.next, val);
            return head;
        }
    }


    /**
     *  如果相等的节点在链表中间，直接操作指针进行删除。如果相等节点在链表头结点，处理起来就很麻烦
     *  使用一个无意义的哨兵结点，作为虚拟头结点。使得链表永不为空，永不无头，可以简化插入和删除的操作
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements1(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel;
        while (head != null){
            if (head.val == val){
                prev.next = head.next;
            }else{
                prev = head;
            }
            head = head.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;

        removeElements(head, 1);
//        System.out.println(removeElements(head).val);
    }
}
