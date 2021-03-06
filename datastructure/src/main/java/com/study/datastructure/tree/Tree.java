package com.study.datastructure.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的定义;
 *  1、树是n（n>=0）个节点的有限集合。n=0时，为空树。
 *  2、节点拥有的子树称为节点的度。度为0则表示该节点为叶子节点或终端节点。树的度是树内各结点的度的最大值。
 *  3、结点的层次，从跟结点开始定义，根为第一层，子节点为第二层。结点的最大层次称为树的深度活高度。
 * 二叉树：
 *  1、n个节点的有限集合，n=0，称为空二叉树。由一个根节点和两棵互不相交的子树组成，分为左子树和右子树。
 *  2、二叉树不存在度大于2的结点。且左子树和右子树不能颠倒顺序。
 * 斜树：
 *  1、所有节点都只有左子树称为左斜树，所有节点都只有右子树称为右斜树。
 * 满二叉树：
 *  在一棵二叉树中，所有分支节点都存在左子树和右子树，并且所有的叶子节点都在同一层中。
 * 完全二叉树：
 *  对一个n个结点的二叉树按层序编号，如果编号i的节点与同样深度的满二叉树中的编号i的位置相同，就是完全二叉树。
 * 二叉树的性质：
 *  1、在二叉树的第n层上至多存在2ⁿ﹣¹个节点
 *  2、深度为n的二叉树最多有2ⁿ-1个结点
 *  3、对任何一棵二叉树T，如果其终端结点数为n，度为2的结点的个数为k，则n = k + 1;
 *  顺序存储二叉树性质：
 *      父节点位置为n，则他的左孩子为2n+1,右孩子为2n+2
 *      子结点的位置为n，父结点的位置为（n-1）/2
 *
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
//        LinkedList input = new LinkedList(Arrays.asList(new Integer[]{
//                3, 2, 9, null, null, 10, null, null, 8, null, 4
//        }));

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
        System.out.println(treeNode.getData());
//        System.out.println(isSameTree(treeNode,treeNode));

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
