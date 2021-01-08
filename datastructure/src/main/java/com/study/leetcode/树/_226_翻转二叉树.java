package com.study.leetcode.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName 翻转二叉树_226
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/24 18:04
 * @Version 1.0
 * <p>
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class _226_翻转二叉树 {

    /**
     * 思路：
     * 遍历到每个二叉树的节点，然后让其left节点和right节点进行交换。
     * 可以使用前中后序遍历到每个节点，然后再左右交换节点。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     *  层序遍历 二叉树翻转
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode target =  queue.poll();
            TreeNode tmp = target.left;
            target.left = target.right;
            target.right = tmp;
            if (target.left != null){
                queue.offer(target.left);
            }
            if (target.right != null){
                queue.offer(target.right);
            }
        }
        return root;
    }
}
