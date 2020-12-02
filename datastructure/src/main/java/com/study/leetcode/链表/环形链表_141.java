package com.study.leetcode.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName 环形链表_141
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/2 17:58
 * @Version 1.0
 */
public class 环形链表_141 {
    /**
     * 给定一个链表，判断链表中是否有环。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * <p>
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     * 进阶：
     * <p>
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     */
    public static  boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode quick = head;
        ListNode slow = head;
        while (quick.next != null && quick.next.next != null){
            quick= quick.next.next;
            slow = slow.next;
            if (quick == slow){
                return true;
            }
        }
        return  false;
    }

    public static  boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        Set<ListNode> set = new HashSet<>();

        while (head != null){
            if (set.contains(head)){
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return  false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head2;

        boolean flag = hasCycle(head);
        System.out.println(flag);

    }
}
