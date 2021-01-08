package com.study.leetcode.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName 二叉树的中序遍历_94
 * @Description
 * @Author za-yaowei
 * @Date 2021/1/6 19:29
 * @Version 1.0
 */
public class _94_二叉树的中序遍历 {


    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            if (!stack.isEmpty()){
//                TreeNode node = stack.pop();
//                resList.add(node.val);
//                root = node.right;
//            }

            if (root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                resList.add(node.val);
                root = node.right;
            }
        }
        return resList;
    }


    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        inorderTraver(root, resList);
        return resList;
    }

    private void inorderTraver(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        inorderTraver(root.left, resList);
        resList.add(root.val);
        inorderTraver(root.right, resList);
    }
}
