package com.study.leetcode.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * @param
 * @param
 * @return https://leetcode-cn.com/problems/same-tree/
 */
public class 相同的树_100 {


    /**
     * 思路 ：
     * 如果想判断两棵树是否是相同的树，需要遍历两棵树的所有节点并一一进行对比。
     * 通过递归去获取到每一个节点，并进行比较。（理解递归的含义，不要试图跳进递归的过程中）
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 迭代:
     *      也可以使用两个队列，分别来存储两棵树
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode p1 = queue.poll();
            TreeNode q1 = queue.poll();

            if (p1 == null && q1 == null) {

            } else if ((p1 == null && q1 != null) || (p1 != null && q1 == null) || (p1.val != q1.val)) {
                return false;
            } else {
                queue.offer(p1.left);
                queue.offer(q1.left);
                queue.offer(p1.right);
                queue.offer(q1.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        isSameTree1(null, null);
    }

}
