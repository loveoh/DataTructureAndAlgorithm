package com.study.datastructure.tree;

import java.util.*;

/**
 * @ClassName BinarySearchTree
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/24 11:26
 * @Version 1.0
 */
public class BinaryTree<E> {

    // 二叉树结点的个数
    protected int size;
    // 根节点
    protected Node<E> root;

    /**
     * 二叉树的个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 前驱结点：二叉树中序遍历时，该节点的前一个节点为前驱结点
     * 获取节点的前驱结点；
     * 如果该节点有左子树，则该节点的前驱结点为左子树中最右边的那个节点
     * 如果该节点没有左子树，则从该节点的父节点一层一层的往上找，该节点在parent节点的右子树时，该parent节点就是
     * 该节点的前驱结点
     * 如果该节点没有左子树，也没有parent节点，则该节点没有前驱结点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 该节点具有左子树
        Node<E> tmpNode = node.left;
        if (tmpNode != null) {
            while (tmpNode.right != null) {
                tmpNode = tmpNode.right;
            }
            return tmpNode;
        }

        // 该节点没有左子树，前驱结点需要从parent中找
        // 该节点没有父节点，或者该节点是父节点的右子树，则parent就是前驱结点
        while (node.parent != null && node != node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 查找后继结点：二叉树中序遍历时，该节点的后一个结点即为后继结点
     * <p>
     * 如果该结点有右子树，则后继结点为右子树的最左边的节点
     * 如果没有右子树，从parent节点一层一层去找。找到该节点在patent左子树时，patent就是后继界节点
     * 如果没有右子树，也没有parent节点，则没有后继结点
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {

        if (node == null) {
            return null;
        }

        // 结点存在右子树
        Node tmpNode = node.right;
        if (tmpNode != null) {
            while (tmpNode.left != null) {
                tmpNode = tmpNode.left;
            }
            return tmpNode;
        }

        // 当结点是parent的左结点时，则parent就是后继结点
        while (node.parent != null & node != node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 求二叉树的高度
     * @param root
     * @return
     */
    public int hight(Node<E> root){
        if (root == null){
            return 0 ;
        }
        return 1 + Math.max(hight(root.left),hight(root.right));
    }

    /**
     *  迭代求出树的高度
     * @param root
     * @return
     */
    public int high2(Node<E> root){
        if (root == null){
            return 0 ;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int num = 0;
        while (!queue.isEmpty()){
            num++;
            for (int i =0;i< queue.size();i++){
                Node<E> node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return num;
    }



    public void preOrderTraveral() {
        preOrderTraveral(root);
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    public void preOrderTraveral(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preOrderTraveral(node.left);
        preOrderTraveral(node.right);

    }


    public void minOrderTraveral() {
        minOrderTraveral(root);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void minOrderTraveral(Node<E> node) {
        if (node == null) {
            return;
        }
        minOrderTraveral(node.left);
        System.out.println(node.element);
        minOrderTraveral(node.right);

    }


    public void postOrderTraveral() {
        postOrderTraveral(root);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void postOrderTraveral(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderTraveral(node.left);
        postOrderTraveral(node.right);
        System.out.println(node.element);

    }


    public void levelOrder() {
        levelOrder(root);
    }

    /**
     * 层序遍历
     *
     * @param node
     */
    public void levelOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        // 将根节点入队
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            System.out.println(target.element);
            if (target.left != null) {// 将左结点入队
                queue.offer(target.left);
            }
            if (target.right != null) { // 将右结点入队
                queue.offer(target.right);
            }
        }
    }


    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }


    protected static class Node<E> {
        // 存储的元素
        E element;
        // 左子结点
        Node<E> left;
        // 右子结点
        Node<E> right;
        // 父节点
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        /**
         * 判断该节点是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断该节点是否有两个子结点
         *
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /**
         *  判断该节点是否是左子结点
         * @return
         */
        public boolean isLeftChild (){
            return left == parent.left;
        }

        /**
         *  判断该节点是否是右子结点
         * @return
         */
        public boolean isRightChild(){
            return right ==  parent.right;
        }

        public Node<E> sibling(){
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()){
                return parent.left;
            }
            return null;
        }
    }

}
