package com.study.datastructure.binarysorttree;

/**
 * Created by loveoh on 2020/8/29.
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree bst = new BinarySortTree();
        for (int value :arr){
            bst.insert(new Node(value));
        }
        bst.midSerach(bst.root);

        Node node = bst.search(5);
        System.out.println(node.value);

        Node node1 = bst.searchParent(5);
        System.out.println(node1.value);
        System.out.println("删除子节点");
        bst.deleteNode(1);
        bst.midSerach(bst.root);
        System.out.println("删除有一个子树的节点");
        bst.deleteNode(3);
        bst.midSerach(bst.root);
        System.out.println("删除有两个个子树的节点");
        bst.deleteNode(7);
        bst.midSerach(bst.root);
    }
}
