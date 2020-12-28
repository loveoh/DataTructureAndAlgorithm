package com.study.datastructure.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinarySearchTree
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/24 11:26
 * @Version 1.0
 */
public class BinarySearchTree<E> {

    // 二叉树结点的个数
    private int size;
    // 根节点
    private Node<E> root;
    // 结点比较器
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 添加结点
     *
     * @param element
     */
    public void add(E element) {
        elementNotNullCheck(element);
        // 如果是空树，直接赋值给root结点
        if (root == null) {
            root = new Node<>(element, null);
            return;
        }
        // 找打父节点
        Node node = root;
        // 用于存储遍历的每个节点的父节点
        Node parent = root;
        int cmp;
        do {
            parent = node;
            cmp = compare(element, (E) node.element);
            if (cmp > 0) { // 插入的值大于当前节点的值
                node = node.right;
            } else if (cmp < 0) { // 插入的节点小于当前节点
                node = node.left;
            } else { // 相等
                return;
            }
        } while (node != null);
        // 需要插入到当前节点的右子树
        if (cmp > 0) {
            parent.right = new Node(element, parent);
        } else {
            parent.left = new Node(element, parent);
        }
        size++;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);

    }

    public void remove(E element) {
        remove(Node(element));
    }

    /**
     * 删除二叉树结点
     * 1、删除的节点度为0，该节点为叶子节点，直接删除就好。
     * 2、删除的节点度为1，该节点只有一个子树，将该节点的parent的指针，指向该节点的子树
     * 3、删除的节点度为2，找到该节点前驱结点，或者后继结点，将前驱结点或者后继结点的值覆盖原值，
     * 并将该节点指向前驱结点或者后继结点的指针置为null
     *
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        // 表示要删除的节点的度为2
        if (node.hasTwoChildren()) {
            //获取前驱结点
            Node<E> proNode = predecessor(node);
            // 将前驱结点的值覆盖要删除的结点
            node.element = proNode.element;
            node = proNode;
        }
        // 要删除的结点的度为0或者1
        Node<E> replaceNode = node.left != null ? node.left : node.right;
        if (replaceNode != null) { // 要删除的节点的度为1
            // replaceNode 的父节点 指向node的父节点
            replaceNode.parent = node.parent;
            if (node.parent == null){ // 要删除的节点为root节点
                root = replaceNode;
            }else if (node == node.parent.right){
                node.parent.right = replaceNode;
            }else {
                node.parent.left = replaceNode;
            }
        }else{
            if (node.parent == null ){// 叶子节点并且是根节点
                root = null;
            }else if (node == node.parent.right){
                node.parent.right = replaceNode;
            }else {
                node.parent.left = replaceNode;
            }
        }
    }


    /**
     * 根据结点值，获取该节点
     *
     * @param element
     * @return
     */
    private Node<E> Node(E element) {
        if (element == null) {
            return null;
        }
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }


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
    private Node<E> predecessor(Node<E> node) {
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
    private Node<E> successor(Node<E> node) {

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


    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private static class Node<E> {
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
    }

}
