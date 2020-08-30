package com.study.datastructure.binarysorttree;

/**
 * Created by loveoh on 2020/8/29.
 */
public class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    /**
     * 二叉排序树的中序遍历
     *
     * @param node
     */
    public void midSeach(Node node) {
        if (node == null) {
            return;
        }
        midSeach(node.left);
        System.out.println(node.value);
        midSeach(node.right);
    }


    /**
     * 二叉树节点添加方法
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value > node.value) {
            if (this.left == null) {
                left = node;
            } else {
                left.add(node);
            }
        } else {
            if (this.value < node.value) {
                if (this.right == null) {
                    right = node;
                } else {
                    right.add(node);
                }
            }
        }
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找该节点的父节点
     * @param value
     * @return
     */
    public Node searchParent(int value) {

        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value){
            return this;
        }else if(value < this.value){
            if (this.left == null){
                return null;
            }
            return this.left.searchParent(value);
        }else{
            if (this.right == null){
                return null;
            }
            return  this.right.searchParent(value);
        }
    }
}
