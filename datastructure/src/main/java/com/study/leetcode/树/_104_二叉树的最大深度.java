package com.study.leetcode.树;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _104_二叉树的最大深度 {

    /**
     * 递归求二叉树的最大深度
     * @param root
     * @return
     */
    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth1(root.left),maxDepth1(root.right));
    }


    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int num = 0;
        while (!queue.isEmpty()) {
            num++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {

        LinkedList input = new LinkedList(Arrays.asList(new Integer[]{
                0, 2, 4, 1, null, 3, -1, 5, 1, null, 6, null, 8
        }));
        TreeNode binaryTree = createBinaryTree(input);
        System.out.println(maxDepth(binaryTree));

    }

    public static TreeNode createBinaryTree(LinkedList<Integer> input) {
        TreeNode root = null;

        if (input == null || input.isEmpty()) {
            return null;
        }
//        LinkedList input = new LinkedList(Arrays.asList(new Integer[]{
//                0,2,4,1,null,3,-1,5,1,null,6,null,8
//        }));

        Integer data = input.removeFirst();
        if (data != null) {
            root = new TreeNode(data);
            root.left = createBinaryTree(input);
            root.right = createBinaryTree(input);
        }
        return root;
    }
}
