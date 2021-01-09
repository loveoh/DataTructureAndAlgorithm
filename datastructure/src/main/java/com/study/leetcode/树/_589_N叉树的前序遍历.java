package com.study.leetcode.树;

import org.springframework.util.CollectionUtils;

import java.util.*;

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
public class _589_N叉树的前序遍历 {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();

        perorder(root, res);
        return res;
    }

    /**
     * 后续遍历，先递归遍历节点，在操作节点
     * @param node
     * @param res
     */
    private void postOrder(Node node, List<Integer> res) {
        if (node == null) {
            return;
        }
        for (Node child : node.children){
            postOrder(child,res);
        }
        res.add(node.val);
    }

    /**
     *  后续遍历，迭代
     *  先遍历子结点子树，再遍历根节点
     * @param node
     * @return
     */
    public List<Integer>  postOrder1(Node node) {
        List<Integer> res = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<Node>();
        if (node == null) return res;
        stack.add(node);
        while (!stack.isEmpty()){
            Node node1 = stack.poll();
            res.add(node1.val);
            for (Node child :node1.children){
                stack.push(child);
            }
        }
        // 将list翻转，得到的就是后续遍历的节点
        Collections.reverse(res);
        return res;
    }

    /**
     * 都是套路：
     * 多叉树的前序遍历，前序遍历就是在循环子结点之前做自己的逻辑操作
     * 多叉树的后续遍历，就是在循环遍历子结点之后做自己的逻辑操作
     *
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

    /**
     * 迭代 前序遍历
     * @param node
     */
    public List<Integer> perorder1(Node node) {
        List<Integer> res = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<Node>();
        if (node == null) return res;

        stack.add(node);
        while (!stack.isEmpty()){
            // 取出链表中最后一个元素
            Node curNode = stack.pollLast();
            res.add(curNode.val);
            // 翻转子结点
            Collections.reverse(curNode.children);
            for (Node child :curNode.children){
                stack.add(child);
            }
        }
        return res;
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
