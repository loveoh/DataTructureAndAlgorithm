package com.study.leetcode.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _145_二叉树的后序遍历 {

    /**
     * 迭代
     * 后序遍历：先遍历左子树，再遍历右子树，最后取根节点
     *  对主要步骤进行逆序处理
     *  1、先取根节点，插入list的最后
     *  2、遍历右子树
     *  3、遍历左子树
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root == null){
            return resList;
        }
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.push(root);// 先遍历根节点
                // 按照头插法将根节点放入到list中
                resList.add(0,root.val);
                root = root.right;// 遍历右子树
            }else {
                TreeNode node = stack.pop();
                root = node.left;// 遍历左子树
            }
        }
        return resList;
    }








        /**
         * 递归
         * @param root
         * @return
         */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        postorderTraver(root,resList);
        return resList;
    }

    private void postorderTraver(TreeNode root, List<Integer> resList) {
        if (root == null){
            return ;
        }
        postorderTraver(root.left,resList);
        postorderTraver(root.right,resList);
        resList.add(root.val);
    }



}
