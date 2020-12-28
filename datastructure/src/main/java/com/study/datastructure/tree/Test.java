package com.study.datastructure.tree;

/**
 * @ClassName Test
 * @Description
 * @Author za-yaowei
 * @Date 2020/12/24 17:39
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        binarySearchTree.add(7);
        binarySearchTree.add(5);
        binarySearchTree.add(6);
        binarySearchTree.add(3);
        binarySearchTree.add(9);
        binarySearchTree.add(11);

        binarySearchTree.minOrderTraveral();
        binarySearchTree.levelOrder();


    }
}
