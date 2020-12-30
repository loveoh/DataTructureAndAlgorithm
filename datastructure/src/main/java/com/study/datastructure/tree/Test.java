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
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.add(7);
        binaryTree.add(5);
        binaryTree.add(6);
        binaryTree.add(3);
        binaryTree.add(9);
        binaryTree.add(11);

        binaryTree.minOrderTraveral();
        binaryTree.levelOrder();


    }
}
