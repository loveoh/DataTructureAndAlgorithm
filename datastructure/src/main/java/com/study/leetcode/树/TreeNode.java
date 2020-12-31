package com.study.leetcode.树;

/**
 * @ClassName TreeNode
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/24 18:05
 * @Version 1.0
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;


    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
