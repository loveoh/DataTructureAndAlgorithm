package com.study.datastructure.tree;

import java.util.Comparator;

/**
 * @ClassName BST
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/28 17:24
 * @Version 1.0
 */
public class BST<E> extends BinaryTree<E> {

    // 结点比较器
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
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
            root = createNode(element, null);
            return;
        }
        // 找到父节点
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
        Node<E> newNode = createNode(element,parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 添加元素之后进行调整树的平衡
        afterAdd(newNode);
    }

    protected void afterAdd(Node<E> newNode) {

    }

    protected void afterRemove(Node<E> newNode) {

    }


    public void remove(E element) {
        remove(node(element));
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
        size--;
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
            if (node.parent == null) { // 要删除的节点为root节点
                root = replaceNode;
            } else if (node == node.parent.right) {
                node.parent.right = replaceNode;
            } else {
                node.parent.left = replaceNode;
            }
            afterRemove(node);
        } else {
            if (node.parent == null) {// 叶子节点并且是根节点
                root = null;
            } else if (node == node.parent.right) {
                node.parent.right = replaceNode;
            } else {
                node.parent.left = replaceNode;
            }
            afterRemove(node);
        }
    }

    /**
     *  判断该节点是否在二叉查找树中
     * @param element
     * @return
     */
    public boolean contains(E element){
        return node(element) != null;
    }

    /**
     * 根据结点值，获取该节点
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
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

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);

    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
