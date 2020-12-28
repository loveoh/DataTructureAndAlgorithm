package com.study.leetcode.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName 二叉树的层序遍历_102
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/24 20:48
 * @Version 1.0
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class 二叉树的层序遍历_102 {

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            // size表示当前层的元素的个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode target = queue.poll();
                list.add(target.val);
                if (target.left != null) {
                    queue.offer(target.left);
                }
                if (target.right != null) {
                    queue.offer(target.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
