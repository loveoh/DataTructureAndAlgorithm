package com.study.datastructure.avl;

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
     * 获取当前节点的高度
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 获取左子树的高度
     * @return
     */
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    /**
     * 获取右子树的高度
     * @return
     */
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
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
        //新增节点后,判断是否是平衡二叉树
        // 左子树的高度与右子树的高度差大于1,则进行右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 双旋转
            if (left !=null&& left.leftHeight()> left.rightHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
            }
        }
        // 右子树的高度与左子树的高度差大于1,则进行左旋
        if (rightHeight() - leftHeight() >1){
            // 单旋转
            if (right != null && right.rightHeight() <right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
        }

    }

    /**
     * 坐旋转
     */
    private void leftRotate() {
        //1.创建一个新节点,值等于当前节点的值
        Node newNode = new Node(this.value);
        //2.把新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //3.把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;
        //4.把当前节点的值替换成右子树的值
        this.value = this.right.value;
        // 5.把当前节点的右子树设置为当前节点的右子树的右子树
        this.right = this.right.right;
        //6.把当前节点的左节点设置为新节点
        this.left = newNode;

    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        //1.创建一个新节点,值等于当前节点
        Node newNode = new Node(value);
        //2.把新节点的右子树设置为当前节点的右子树
        newNode.right = this.right;
        //3.把新节点的做子树设置为当前节点的左子树的右子树
        newNode.left = this.left.right;
        //4.将当前节点的值替换为左子树的值
        this.value = this.left.value;
        //5.把当前节点左子树替换成为左子树的左子树
        this.left = this.left.left;
        //6.把当前节点的右节点替换成新节点
        this.right = newNode;
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
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {

        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.searchParent(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.searchParent(value);
        }
    }
}
