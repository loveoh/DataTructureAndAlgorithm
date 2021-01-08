package com.study.datastructure.tree;

import java.util.Comparator;

/**
 * @ClassName RBTree
 * @Description
 * @Author za-yaowei
 * @Date 2021/1/6 11:55
 * @Version 1.0
 */
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     *  添加结点后调整红黑树
     *  我们默认把添加的节点设置为RED，这样可以尽可能的满足5个条件中的4个，只有第4个条件不满足。需要调整
     *
     * @param node 添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent =  node.parent;
        // 表示此时新增的节点是根节点。只需要将节点染黑。
        if (parent == null) {
            // 将节点染黑
            black(node);
            return;
        }
        // 添加结点的父节点如果是黑色，不用做任何处理，符合红黑树的条件
        if (isBlack(parent)){
            return;
        }
        // 获取叔父节点
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        // 叔父节点是红色，符合4阶B树的节点上溢的情况
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            red(grand);
            // 将根节点染红，递归调用添加逻辑
            afterAdd(grand);
            return;
        }
        // uncle节点不为RED的情况
        if (parent.isLeftChild()){//L
            if (node.isLeftChild()){ //LL
                // parent染黑，grand染红，并进行右旋转
                black(parent);
                red(grand);
                rotateRight(grand);
            }else{ //LR
                // 把自己染黑，grand染红，parent左旋，grand右旋
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{
            if (node.isRightChild()){// RR
                // parent染黑，grand染红，并进行左旋转
                black(parent);
                red(grand);
                rotateLeft(grand);
            }else{//RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * 删除结点后调整红黑树
     * 删除红黑树节点都发生在叶子节点当中。主要分为三种情况
     * 1.删除的节点度为2，这种情况不用考虑，找到该节点的前驱结点，将值进行替换，然后再删除前驱结点。这种情况就是以下两种情况中的一种
     * 2、删除结点度为1的，该子结点是RED的BLACK节点
     * 3、删除BLACK叶子节点
     *
     *
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        // 删除的节点是红色或者用于取代删除结点的子结点是红色
        if (isRed(node)){
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        if (parent == null){// 表示删除的是root节点
            return;
        }
        // 删除的是黑色叶子节点[下溢]
        // 判断删除的节点是左结点还是右节点
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left){// 删除的是左结点，兄弟节点是右节点
            if (isRed(sibling)){//兄弟节点是红色的
                black(sibling);
                red(parent);
                rotateLeft(sibling);
                sibling = parent.right;

            }


        }else{
            if (isRed(sibling)){//兄弟节点是红色
                black(sibling);
                red(parent);
                // 父节点向右旋转
                rotateRight(parent);
                // 更换兄弟节点
                sibling = parent.left;
            }

            // 处理兄弟节点为black的情况
            // 兄弟节点是黑色，并且没有红色的子结点（父节点需要下溢）
            if (isBlack(sibling.left) && isBlack(sibling.right)){
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack){// 父节点是黑色，把parent当做被删除的节点递归处理。
                    afterRemove(parent);
                }
            }else {
                // 兄弟节点有红色的子结点，可以从子结点中借用节点，不需要父节点下溢
                // 兄弟节点的左结点时黑色，兄弟要先旋转
                if (isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }

        }
    }

    private Node<E> color(Node<E> node , boolean color){
        if (node == null) {
            return node;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    private boolean colorOf(Node node){
        // 红黑树空节点 默认为黑色
        return node == null ?BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
