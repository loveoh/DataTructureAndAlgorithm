package com.study.leetcode.树;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树的锯齿形层序遍历_103 {


    /**
     *  思路：
     *      使用二叉树的层序遍历，将每一层的元素放入到一个list中。
     *      奇数层是从左到右的顺序，偶数层是从右往左的顺序
     *      如何判断元素是在同一个层上面呢 ？
     *      root节点入队后，队列中的元素个数为1，第一个结点出队的同时，把该节点的左右子结点都入队，
     *      此时的队列中的元素都是在同一个层级。
     *      使用一个双端队列，来保存每一个层级的元素,
     *      奇数层的元素，使用从头部插入队列，
     *      偶数层的元素，使用尾插发插入队列。
     *
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int num = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> deque = new LinkedList<>();
            // 遍历同一个层级的元素
            for (int i = 0; i < size; i++) {
                TreeNode target = queue.poll();
                if (num % 2 != 0) {
                    deque.addFirst(target.val);
                } else {
                    deque.addLast(target.val);
                }

                if (target.left != null) {
                    queue.offer(target.left);
                }
                if (target.right != null) {
                    queue.offer(target.right);
                }
            }
            res.add(new LinkedList<>(deque));
            num++;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        zigzagLevelOrder(treeNode);
    }
}
