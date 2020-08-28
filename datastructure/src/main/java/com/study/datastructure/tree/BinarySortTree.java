package com.study.datastructure.tree;

import java.math.BigDecimal;

/**
 * @ClassName BinarySortTree
 * @Description
 * @Author za-yaowei
 * @Date 2020/8/25 12:12
 * @Version 1.0
 */
public class BinarySortTree {

    private Node root;

    /**
     * 二叉排序树新增结点
     * @param node
     */
    public void insert(Node node){
        if (root == null){
            root = node;
        }
        if (node.value < this.root.value){
               if(root.left == null){
                   root.left = node;
               }else{
                   insert(node);
               }

        }else{

        }

    }









    class Node{
        private int value;
        private Node left;
        private Node right;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
