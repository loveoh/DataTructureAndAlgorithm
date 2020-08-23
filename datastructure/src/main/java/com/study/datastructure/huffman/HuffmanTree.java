package com.study.datastructure.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 把数组创建huffman树
 * 1、使用数组中的元素创建出多个二叉树（只有根节点），放入到list中，并排序。
 * 2、取出权重最小的两个二叉树，将权重想加，生成新的二叉树，权重小的做为新二叉树的左子树
 * 权重大的作为新二叉树的右子树。将新二叉树add到原先的list中，且删掉原来最小的两个二叉树
 * 3、当list中的元素只有一个时，退出循环，huffman树创建完成。
 * Created by loveoh on 2020/8/22.
 */
public class HuffmanTree {

    public Node creatHuffmanTree(int[] arr) {
        // 创建二叉树列表，并放入二叉树
        List<Node> nodeList = new ArrayList<>();
        for (int weight : arr) {
            nodeList.add(new Node(null, weight));
        }
        while (nodeList.size() > 1) {
            // 针对list进行排序
            Collections.sort(nodeList);
            // 取出最小的二叉树
            Node left = nodeList.get(nodeList.size() - 1);
            // 取出第二小的二叉树
            Node right = nodeList.get(nodeList.size() - 2);
            // 创建新的二叉树,并设置其子树
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            // 设置左子树
            parent.left = left;
            // 设置右子树
            parent.right = right;
            nodeList.add(parent);
            // 删除原先的二叉树
            nodeList.remove(left);
            nodeList.remove(right);
        }
        System.out.println(nodeList.get(0));
        return nodeList.get(0);

    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        int[] arr = {3, 4, 5, 7, 92, 1};
        huffmanTree.creatHuffmanTree(arr);

    }

}
