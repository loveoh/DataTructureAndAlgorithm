package com.study.leetcode.树;


/**
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * <p>
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 * 解释：另一个满足题目要求可以通过的树是：
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 * 示例 3：
 * <p>
 * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * 输出：[4,2,7,1,3,5]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉搜索树中的插入操作_701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 如果根节点是null
        if (root == null) {
            TreeNode treeNode = new TreeNode(val);
            root = treeNode;
            return root;
        }
        // 使用递归添加结点
        addNode(root, val);
        return root;
    }

    private void addNode(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right != null) {
                addNode(root.right, val);
            } else {
                root.right = new TreeNode(val);
            }
        } else {
            if (root.left != null) {
                addNode(root.left, val);
            } else {
                root.left = new TreeNode(val);
            }
        }
    }

    /**
     * 使用迭代添加结点
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        // 如果根节点是null
        if (root == null) {
            TreeNode treeNode = new TreeNode(val);
            root = treeNode;
            return root;
        }
        // 用来保存插入结点的父节点
        TreeNode parent = root;
        // 临时结点，在每次循环中，保存该节点的子结点
        TreeNode tmpNode = root;
        int num = 0;
        while (tmpNode != null) {
            parent = tmpNode;
            num = tmpNode.val - val;
            if (num > 0) {
                tmpNode = tmpNode.left;
            } else {
                tmpNode = tmpNode.right;
            }
        }
        if (num > 0) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
        return root;
    }
}
