package com.study.leetcode.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 */
public class _144_二叉树的前序遍历 {

    /**
     * 前序遍历，迭代
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Stack<TreeNode> stack = new Stack<>();
//        while (!stack.isEmpty() || root != null) {
//            while (root != null) {
//                resList.add(root.val);
//                stack.push(root);
//                root = root.left;
//            }
//            if (!stack.isEmpty()){
//                TreeNode node = stack.pop();
//                root = node.right;
//            }
//        }
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                resList.add(root.val);
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                root = node.right;
            }

        }
        return resList;
    }


    /**
     * 前序遍历，递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        preorderTraver(root, resList);

        return resList;
    }

    private void preorderTraver(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        resList.add(root.val);
        preorderTraver(root.left, resList);
        preorderTraver(root.right, resList);
    }

    public static void main(String[] args) {
        Stack<TreeNode> stack = new Stack<>();
        stack.pop();
    }
}
