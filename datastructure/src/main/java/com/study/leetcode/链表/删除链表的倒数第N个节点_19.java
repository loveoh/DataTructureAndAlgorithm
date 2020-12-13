package com.study.leetcode.链表;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName 删除链表的倒数第N个节点_19
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/8 12:45
 * @Version 1.0
 */
public class 删除链表的倒数第N个节点_19 {
    /**
     * 首先遍历链表，获取链表的长度size，(size-n)就是要删除的节点。
     * 使用sential节点作为虚拟头结点，循环遍历，获取到需要删除的节点，将该节点的指针指向next.next
     * 加入链表的长度为K，则时间复杂度为 O(2K - n)
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        ListNode sential = new ListNode(0);
        sential.next = head;
        ListNode prve = sential;

        for (int i = 0; i < size - n; i++) {
            prve = head;
            head = head.next;
        }
        prve.next = head.next;
        return sential.next;
    }

    /**
     * 借助stack完成，将链表push到栈中，然后再出栈N个节点，此时栈顶元素就是要删除节点的前驱结点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {

        if (head == null) {
            return null;
        }
        ListNode sential = new ListNode(0);
        sential.next = head;

        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        for (int i = 0;i <n;i++){
            stack.pop();
        }
        if (stack.size()==0){
            return sential.next.next;
        }
        ListNode prve = stack.peek();
        prve.next = prve.next.next;
        return sential.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
//        head4.next = head2;

        System.out.println(removeNthFromEnd1(head, 4).val);

    }
}
