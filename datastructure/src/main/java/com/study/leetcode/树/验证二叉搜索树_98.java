package com.study.leetcode.树;


/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 验证二叉搜索树_98 {


    public boolean isValidBST(TreeNode root) {





        return checkBST(root,null,null);
    }


    public boolean isValidBST1(TreeNode root) {

        return checkBST(root,null,null);
    }

    /**
     *  递归去比较每一个节点。 节点的左子树要小于 lower，节点的右子树要大于higer
     *  每一层递归要重新更新lower 或者 higer
     * @param root 节点
     * @param lower 该节点所在范围的最小值
     * @param higher 该节点所在范围的最大值
     * @return
     */
    private boolean checkBST(TreeNode root, Integer lower, Integer higher) {
        if (root == null){
            return true;
        }
        int val = root.val;
        if (lower != null && val <= lower){
            return false;
        }
        if (higher != null && val >= higher){
            return false;
        }
        // 替换
        return checkBST(root.left,lower,val) && checkBST(root.right,val,higher);

//        return false;
    }


    /**
     * 失败案例，这样判断只能保证 当前节点的左右节点符合二叉搜索树,不能保证整棵树是二叉搜索树
     * @param root
     * @return
     */
    private boolean checkBST1(TreeNode root) {
        if (root == null){
            return true;
        }
        if (root.left != null &&root.left.val >= root.val){
            return false;
        }
        if ( root.right != null &&root.right.val <= root.val){
            return false;
        }
       return checkBST1(root.left) && checkBST1(root.right);
    }
}
