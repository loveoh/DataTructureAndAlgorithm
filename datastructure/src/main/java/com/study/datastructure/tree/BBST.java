package com.study.datastructure.tree;

import java.util.Comparator;

/**
 * @ClassName BBST
 * @Description
 * @Author za-yaowei
 * @Date 2021/1/6 11:45
 * @Version 1.0
 */
public class BBST<E> extends BST<E> {

    public BBST() {
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 节点左旋转
     *
     * @param grand
     */
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        parent.left = grand;
        grand.right = child;
        afterRotate(grand, parent, child);
    }

    /**
     * 节点右旋转
     *
     * @param grand 需要调整的节点
     */
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        parent.right = grand;
        grand.left = child;
        afterRotate(grand, parent, child);

    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新移动节点的父节点指针
        // 把parent节点转变成子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;

    }

}
