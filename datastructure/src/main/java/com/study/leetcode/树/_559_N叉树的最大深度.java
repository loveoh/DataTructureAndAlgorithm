package com.study.leetcode.树;

import java.util.*;

/**
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _559_N叉树的最大深度 {

    /**
     *  迭代
     *      层序遍历，记录N叉树的层数，遍历结束，层数就是高度
     *      难点 ： 如何判断这是N叉树的一层？
     *              将子结点插入的队列中后，记录当前队列的长度n。从0到n就是一层。
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        int high = 0;
        if (root == null) {
            return high;
        }
        LinkedList<Node> stack = new LinkedList<Node>();
        stack.add(root);
        while (!stack.isEmpty()) {
            high++;
            // 记录当前队列中的节点树，这些节点都是一层的节点
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                Node curNode = stack.poll();
                for (Node child : curNode.children) {
                    stack.add(child);
                }
            }
        }
        return high;
    }

    /**
     * 递归实现
     *      递归遍历每一个节点，获取节点的高度。并求出最大值。一层一层的比较
     * @param root
     * @return
     */
    public int maxDepth1(Node root) {
        if (root == null){
            return 0;
        }else if (root.children.isEmpty()){
            return 1;
        }else {
            List<Integer> highList = new ArrayList<>();
            for (Node node : root.children){
                // 递归获取节点的高度
                highList.add(maxDepth1(node));
            }
            return Collections.max(highList);
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
