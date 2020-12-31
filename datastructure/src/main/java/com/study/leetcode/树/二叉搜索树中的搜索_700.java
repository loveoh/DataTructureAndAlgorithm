package com.study.leetcode.树;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉搜索树中的搜索_700 {

    /**
     *  使用迭代搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return root;
        }
        TreeNode tmpNode = root;
        while (tmpNode != null){
            if (tmpNode.val > val){
                tmpNode = tmpNode.left;
            }else if (tmpNode.val < val){
                tmpNode = tmpNode.right;
            }else {
                return tmpNode;
            }
        }
        return  null;
    }

    /**
     *  递归获取二叉搜索树的节点
     * @param root
     * @param val
     * @return
     */
    public static TreeNode searchBST1(TreeNode root, int val) {
        if (root == null){
            return root;
        }

        return searchNode(root,val);
    }

    private static TreeNode searchNode(TreeNode node, int val) {
        if (node == null){
            return null;
        }
        if (node.val < val){
           return searchNode(node.right,val);
        }else if(node.val > val){
            return searchNode(node.left,val);
        }else {
            return node;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);

    }
}
