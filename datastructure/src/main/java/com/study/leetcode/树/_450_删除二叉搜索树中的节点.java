package com.study.leetcode.树;

import com.study.datastructure.tree.Tree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _450_删除二叉搜索树中的节点 {

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return root;
        }
        // 比当前节点大，去当前节点的左子树中找并删除
        if (root.val > key){
            root.left = deleteNode(root.left,key);
        }else if (root.val < key){
            // 比当前节点小，去当前节点右子树中查找并删除
            root.right = deleteNode(root.right,key);
        }else{
            // 当前节点就是要删除的节点
            if (root.left != null && root.right != null){// 要删除的节点的度为2
                // 找到要删除结点的前驱结点,再找到该节点的前驱结点，将前驱结点的值和要删除的节点值进行交换。
                // 然后再删除该前驱结点
                TreeNode preNode = searchPreNode(root);
                root.val = preNode.val;
                // 递归删除前驱结点 （不要陷入到递归的逻辑中去，只需要知道递归可以做到什么事情。
                // deleteNode 就是删除结点的功能，删除前驱结点只需要调用就行，不要试图去推到递归的执行逻辑，容易陷入到递归的层次调用）
                // 判断好逻辑关系和边界条件就好
                root.left = deleteNode(root.left,preNode.val);
            }else if (root.left == null || root.right == null){//要删除的节点的度为1
                root = root.left == null ? root.right : root.left;
            }else {
                root = null;
            }
        }
        return root;
    }

    public static TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null){
            return root;
        }
        TreeNode delNode = searchNode(root,key);
        if (delNode == null){
            return root;
        }

        // 要删除的结点度为2
        if (delNode.left != null && delNode.right != null){
            // 获取该节点的前驱结点
            TreeNode preNode = searchPreNode(delNode);
            delNode.val = preNode.val;
            preNode = null;
        }else if (delNode.left == null || delNode.right == null){// 要删除的节点的度为1
                TreeNode node = delNode.left == null ? delNode.right : delNode.left;
                delNode.val = node.val;
                node = null;
        }else{
            // 删除的节点为叶子节点
            delNode = null;
        }
        return root;

    }

    /**
     *   获取该节点的前驱结点
     * @param treeNode
     * @return
     */
    private static TreeNode searchPreNode(TreeNode treeNode) {
            TreeNode preNode = treeNode.left;
            while (preNode.right != null){
                preNode = preNode.right;
            }
            return preNode;
    }

    private static TreeNode searchNode(TreeNode root, int key) {
        while (root != null){
            if (root.val == key){
                return root;
            }else if (root.val < key){
                root = root.right;
            }else {
                root = root.left;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        int[] nums  = new int[]{3,6,2,4,7};
        for (int i = 0 ;i < nums.length;i++){
             insertIntoBST1(root,nums[i]);
        }

        deleteNode(root,3);
     }

    public static TreeNode insertIntoBST1(TreeNode root, int val) {
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
