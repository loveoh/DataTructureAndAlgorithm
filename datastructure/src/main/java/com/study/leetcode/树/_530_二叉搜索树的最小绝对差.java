package com.study.leetcode.树;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 */
public class _530_二叉搜索树的最小绝对差 {
    // 设置一个全局变量，用来保存当前遍历结点的前驱结点，用来计算前驱结点和当前节点的差值
    // 每次遍历后将当前节点赋值给前驱结点，以供后续计算用
    TreeNode prev =  null;
    int min = Integer.MAX_VALUE;

    /**
     *  二叉搜索树的中序遍历，遍历出的节点是有序的，维护两个结点的最小差值
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        getMin(root);
        return min;
    }

    private void getMin(TreeNode root) {
        if(root == null){
            return;
        }
        getMin(root.left);
        // 遍历的第一个结点，此时还没有前驱结点，所以不需要计算节点最小值的差值
        if(prev != null){
            min = Math.min(min,root.val - prev.val);
        }
        // 将当前节点置为前驱结点
        prev = root;
        getMin(root.right);
    }
}
