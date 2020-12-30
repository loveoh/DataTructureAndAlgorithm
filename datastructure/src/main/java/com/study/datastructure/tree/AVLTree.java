package com.study.datastructure.tree;

import java.util.Comparator;


/**
 * @ClassName AVLTree
 * @Description 在添加节点，删除结点的时候，可能会破坏AVL树的平衡，
 * @Author za-yaowei
 * @Date 2020/12/28 17:39
 * @Version 1.0
 */
public class AVLTree<E> extends BST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 添加结点之后，进行AVL树的平衡操作
     *
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        /**
         * 添加结点会导致AVL树失去平衡，从添加的节点开始，往上层遍历，找到最近一个失衡的父节点，进行调整
         *
         */
        while ((node = node.parent) != null) {
            // 判断该节点是否是平衡节点
            if (isBalanced(node)) {
                // 更新该节点的高度
                updateHeight(node);
            } else {
                // 重新平衡AVL树
                rebalance(node);
                // 添加结点导致的AVL树不平衡，只需要调整最近的一个parent节点。
                // 可以直接break
                break;
            }
        }

    }

    /**
     * 7
     * /
     * 5
     * /
     * 4
     * 调整AVL树的平衡
     *
     * @param grand 需要调整的节点 (即上图中的结点7)
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            // LL grand节点进行右旋转
            if (node.isLeftChild()) {
                rotateRight(grand);
            } else {
                // LR parent节点先左旋转，grand结点再右旋转
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            // RR  grand节点进行左旋转
            if (node.isRightChild()) {
                rotateLeft(grand);
            } else {
                // RL  parent节点先向右旋转，grand节点在向左旋转
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * 节点左旋转
     *
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
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
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        parent.right = grand;
        grand.left = child;
        afterRotate(grand, parent, child);

    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
        // 更新节点的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     *  删除结点，要调整AVL树的平衡度。
     *  由于删除会导致整棵树的平衡度发生变化，需要一直向上调整，调整到AVL树的根节点为止。
     *
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断该节点是否是平衡节点
            if (isBalanced(node)) {
                // 更新该节点的高度
                updateHeight(node);
            } else {
                // 重新平衡AVL树
                rebalance(node);
            }
        }
    }

    /**
     * 判断该节点是否是平衡节点
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) >= 1;
    }

    /**
     * 更新该节点的高度
     *
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }


    /**
     * AVL树的节点 需要维护一个该节点的高度
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {
        private int height = 0;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 获取该结点的平衡因子
         *
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新该节点的高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 获取该节点较高的子树节点，如果节点的高度相等。
         * 返回该节点在parent中的位置的子结点。
         * 该节点是parent的左子树，则返回该节点的左子结点，
         * 该节点是parent的右子树，则返回该节点的右子结点
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }


    }

}


