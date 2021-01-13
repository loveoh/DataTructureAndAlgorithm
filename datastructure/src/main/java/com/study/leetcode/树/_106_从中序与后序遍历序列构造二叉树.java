package com.study.leetcode.树;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _106_从中序与后序遍历序列构造二叉树 {

    // 用来存储中序遍历时节点以及节点的位置关系
   static Map<Integer, Integer> nodeMap = new HashMap<>();
    // 用来存储后续遍历的节点
   static int[] post;

    /**
     * 根据中序遍历 和后续遍历，可以确定根节点
     *
     * @param inorder   中序遍历的数组
     * @param postorder 后序遍历的数组
     * @return
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        // 保存中序遍历的节点与节点书索引的关系
        for (int i = 0; i < inorder.length; i++) {
            nodeMap.put(inorder[i], i);
        }
        post = postorder;

        TreeNode root = buildTree(0, inorder.length-1, 0, postorder.length-1);
        return root;
    }

    /**
     * 类似于归并排序；通过递归去构建左子树，右子树，一直从底层构建到根节点。
     *  要自己画图，理解节点在中序遍历，后序遍历中的位置关系
     * @param is 中序遍历中，左子树的起始位置
     * @param ie 中序遍历中，左子树结束节点的位置
     * @param ps 后续遍历中，右子树节点的起始位置
     * @param pe 后续遍历中，右子树节点的结束位置
     * @return
     */
    private static TreeNode buildTree(int is, int ie, int ps, int pe) {
        if (is > ie || ps > pe) {
            return null;
        }
        // 后序遍历的最后一个节点 就是root节点
        int root = post[pe];
        // 获取root节点的索引
        int ri = nodeMap.get(root);
        // 创建节点
        TreeNode node = new TreeNode(root);
        // 构建左子树
        node.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
        // 后序节点数组中，哪些是左子树，可以根据中序遍历的数组判断，左子树的数目是一直的
        // 左子树的节点数目是：ri - is ,后序遍历数组中 左子树的最后一个节点的位置是 ps + ri -is + 1
        // 构建右子树
        node.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
        return node;
    }

    public static void main(String[] args) {
       int[] preorder = {3,9,20,15,7};
       int[] inorder = {9,3,15,20,7};

        buildTree(preorder,inorder);
    }

}
