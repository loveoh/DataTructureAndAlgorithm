package com.study.leetcode.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 *
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 *
 * 输入:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _662_二叉树最大宽度 {

    /**
     * 思路： 按照完全二叉树的结构特点来看，如果一个节点元素在数组中的位置为n，则他的左子节点的位置为2n + 1，右子结点的位置为 2n + 2；
     *      如：root的节点的位置为0 ，left为1，right为2
     *       获取该二叉树的最大宽度，可以通过层序遍历，找到每一层的宽度，然后取到最大的宽度
     *       如何获取每一层的宽度呢？
     *       节点维护一个depth属性，表示该节点所在的层数，获取到最左边不为null的节点，并记录位置，当前宽度就是当前节点的位置 -left +1 ；
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        Queue<AnnotateNode> queue = new LinkedList<>();
        queue.offer(new AnnotateNode(root,0,0));
        int curDepth = 0;
        int left = 0;
        int ans = 0;
        while (!queue.isEmpty()){
            AnnotateNode annotateNode = queue.poll();
            if (annotateNode.node != null){
                // 将该节点的子结点入队，此时子结点的层数要+1；并且维护该节点所在的位置。
                queue.offer(new AnnotateNode(annotateNode.node.left,annotateNode.depth+1,annotateNode.pos *2 +1));
                queue.offer(new AnnotateNode(annotateNode.node.right,annotateNode.depth+1,annotateNode.pos *2 +2));
                // 当前节点的高度和出队节点的高度不相等，表示该节点是下一层的节点
                if (curDepth != annotateNode.depth){
                    // 重新记录层高
                    curDepth = annotateNode.depth;
                    // 记录该层中最左边的节点位置。
                    left = annotateNode.pos;
                }
                // 对比并获取最大宽度
                ans = Math.max(ans,annotateNode.pos - left +1);
            }
        }
        return ans;
    }

    class AnnotateNode{
        private TreeNode node;
        // 该节点所在的树的层数
        private int depth;
        // 该节点的位置
        private int pos;

        public AnnotateNode(TreeNode node, int depth, int pos) {
            this.node = node;
            this.depth = depth;
            this.pos = pos;
        }
    }
}
