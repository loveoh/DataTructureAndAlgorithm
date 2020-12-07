package com.study.leetcode.链表;

/**
 * @ClassName 删除排序链表中的重复元素_83
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/3 9:39
 * @Version 1.0
 */
public class 删除排序链表中的重复元素_83 {
    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     *
     * @param head
     * @return
     */

    // 使用迭代解决
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head;
        while (head != null && head.next != null) {
            // 两两相等，删除后一个结点，不移动指针，并且进行下一轮循环
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                // 如果两两不相等，则指针指向下一个。进行下一轮循环
                head = head.next;
            }
        }
        return newHead;
    }

    // 使用递归解决
    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        /**
         * 1,2,3,4,4
         * 当head =4时，head.nex =4 ;
         * 由于 两个结点的值相等，所以返回head.next;
         * 程序跳转到上一层，上一层的结点为3，该节点的next指针 指向最后一个节点，相当于删掉了倒数第二个节点。
         */
        head.next = deleteDuplicates2(head.next);
        if (head.val == head.next.val){
            return head.next;
        }
        return head;
    }

}
