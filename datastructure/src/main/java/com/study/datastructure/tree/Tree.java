package com.study.datastructure.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by loveoh on 2020/5/17.
 */
public class Tree {

    /**
     * 构建二叉树
     *
     * @param input
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Object> input) {
        TreeNode root = null;

        if (input == null || input.isEmpty()) {
            return null;
        }

        Object data = input.removeFirst();
        if (data != null) {
            root = new TreeNode(data);
            root.leftChild = createBinaryTree(input);
            root.rightChild = createBinaryTree(input);
        }
        return root;
    }

    public class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode listNode;
//        ListNode head;
//        Stack<Integer> stack1 = new Stack();
//        while(l1.next != null){
//            stack1.push(l1.val);
//        }
//
//        Stack<Integer> stack2 = new Stack();
//        while(l2.next != null){
//            stack2.push(l1.val);
//        }
//
//        while (stack1 !=null && stack2 != null){
//
//            Integer temp1 = stack1.peek();
//            Integer temp2 = stack2.peek();
//            Integer temp = (temp1 + temp2) >= 10 ? (temp1 + temp2) - 10 : (temp1 + temp2);
//            listNode = new ListNode(temp);
//            head = listNode;
//        }
//
//
//    }

    /**
     * 二叉树的前序遍历
     *
     * @param treeNode
     */
    public static void preOrderTraveral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.data);
        preOrderTraveral(treeNode.leftChild);
        preOrderTraveral(treeNode.rightChild);
    }


    /**
     * 二叉树中遍历
     *
     * @param treeNode
     */
    public static void minOrderTraveral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        minOrderTraveral(treeNode.leftChild);
        System.out.println(treeNode.data);
        minOrderTraveral(treeNode.rightChild);
    }


    /**
     * 二叉树后续遍历
     *
     * @param treeNode
     */
    public static void postOrderTraveral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrderTraveral(treeNode.leftChild);
        postOrderTraveral(treeNode.rightChild);
        System.out.println(treeNode.data);
    }


    /**
     * 二叉树的非递归遍历 前序遍历
     *
     * @param root
     */
    public static void preOrderTraverlaWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 中序遍历 非递归
     *
     * @param root
     */
    public static void minOrderTraveralWhitStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 后序遍历 非递归
     *
     * @param root
     */
    public static void postOrderTraveralWhitStack(TreeNode root) {
        Stack<TreeNode> inputStack = new Stack<>();
        Stack<TreeNode> outputStack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !inputStack.isEmpty()) {
            if (treeNode != null) {
                inputStack.push(treeNode);
                outputStack.push(treeNode);
                treeNode = treeNode.rightChild;
            } else {
                treeNode = inputStack.pop();
                treeNode = treeNode.leftChild;
            }
        }

        while (!outputStack.isEmpty()) {
            treeNode = outputStack.pop();
            System.out.println(treeNode.data);
        }
    }

    /**
     * 广度优先遍历
     *
     * @param root
     */
    public static void levelOrderTraveral(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode treeNode = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            treeNode = queue.poll();
            System.out.println(treeNode.data);
            if (treeNode.leftChild != null) {
                queue.offer(treeNode.leftChild);
            }
            if (treeNode.rightChild != null) {
                queue.offer(treeNode.rightChild);
            }
        }
    }

    /**
     *  查找二叉树的节点
     * @param treeNode
     * @param i
     * @return
     */
    public static TreeNode findByFront(TreeNode treeNode, int i) {
        TreeNode target = null;
        if (treeNode.data.equals(i)) {
            return treeNode;
        } else {
            if (treeNode.leftChild != null) {
                target = findByFront(treeNode.leftChild, i);
            }
            if (target != null) {
                return target;
            }
            if (treeNode.rightChild != null) {
                target = findByFront(treeNode.rightChild, i);
            }
        }
        return target;

    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p != null || q != null){
            return false;
        }
        if(p.data != q.data){
            return false;
        }
        boolean flag1 = isSameTree(p.leftChild,q.leftChild);
        if(!flag1){
            return false;
        }
        boolean flag2 = isSameTree(p.rightChild,q.rightChild);
        if(!flag2){
            return false;
        }else{
            return true;
        }


    }

    public static void main(String[] args) {
        LinkedList input = new LinkedList(Arrays.asList(new Integer[]{
                3, 2, 9, null, null, 10, null, null, 8, null, 4
        }));

        TreeNode treeNode = createBinaryTree(input);
        System.out.println(isSameTree(treeNode,treeNode));

        System.out.println("前序遍历：");
        preOrderTraveral(treeNode);
//        preOrderTraverlaWithStack(treeNode);
//        System.out.println("中序遍历：");
//        minOrderTraveral(treeNode);
//        minOrderTraveralWhitStack(treeNode);
//        System.out.println("后序遍历：");
//        postOrderTraveral(treeNode);
//        postOrderTraveralWhitStack(treeNode);
//        System.out.println("广度优先遍历：");
//        levelOrderTraveral(treeNode);
//        System.out.println("查找树的节点");
//        TreeNode target = findByFront(treeNode,10);
//        System.out.println(target.data);

    }


}
