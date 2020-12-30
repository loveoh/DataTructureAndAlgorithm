package com.study.leetcode.树;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * @ClassName 二叉树的层序遍历II_107
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层序遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树的层序遍历II_107 {

    /**
     * 思路： 使用层序遍历，获取到每一层的节点，然后将每一层的节点放如到一个list中。
     * 将list放入到一个双端队列中，每次都从头部插入。然后再从头开始遍历，list放入到res中。
     * Arraylist 有个方法是 add(index,node);使用该api可以每次将list放入到第一个索引位置。
     * 这样的话，就可以省略双端队列。
     * @param root
     * @return
     */

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
//        Deque<List<Integer>> deque = new LinkedList();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
//            deque.addFirst(list);
            res.add(0,list);
        }
//        while (!deque.isEmpty()) {
//            res.add(deque.poll());
//        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(0,22);
        System.out.println(res);
    }
}
