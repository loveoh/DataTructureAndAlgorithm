package com.study.leetcode.树;

import java.util.ArrayList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 */
public class N叉树的前序遍历_589 {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();

        perorder(root, res);
        return res;
    }

    /**
     *   都是套路：
     *      多叉树的前序遍历，前序遍历就是在循环子结点之前做自己的逻辑操作
     *      多叉树的后续遍历，就是在循环遍历子结点之后做自己的逻辑操作
     * @param node
     * @param res
     */
    private void perorder(Node node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        for (Node node1 : node.children) {
            perorder(node1, res);
        }
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
};
