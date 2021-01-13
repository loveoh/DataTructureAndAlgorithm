package com.study.leetcode.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _114_二叉树展开为链表 {

    /**
     * 前序遍历形成链表，然后在遍历链表。拼接到原先的二叉树中
     * @param root
     */
    public void flatten1(TreeNode root) {
        if (root == null){
            return;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if (root != null){
                nodeList.add(root);
                stack.add(root.left);
            }else {
                TreeNode tmpNode = stack.pop();
                root = tmpNode.right;
            }
        }
        for (int i = 1; i < nodeList.size(); i++) {
            TreeNode pre = nodeList.get(i -1);
            TreeNode cur = nodeList.get(i);
            pre.right = cur;
            pre.left = null;
        }
    }


    /**
     *  使用递归
     *      1、把左子树变成链表
     *      2、把右子树变成链表
     *      3、将右子树变成的链表，拼接到左子树变成的链表的最后一个节点上。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        // 将左子树展开成链表
        flatten(root.left);
        // 将右子树展开成链表
        flatten(root.right);
        // 临时保存右子树链表
        TreeNode tmp = root.right;
        //  将左子树链表拼接到右节点上
        root.right = root.left;
        // 置空左子树
        root.left = null;
        // 找到左子树链表的最后一个节点
        while (root.right.right != null){
            root = root.right;
        }
        // 将右子树展开的链表拼接在左子树上
        root.right = tmp;
    }
}
