package com.study.datastructure.avl;

/**
 * @ClassName BinarySortTree
 * @Description
 * @Author za-yaowei
 * @Date 2020/8/25 12:12
 * @Version 1.0
 */
public class BinarySortTree {

    Node root;

    /**
     * 二叉排序树新增结点
     *
     * @param node
     */
    public void insert(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void midSerach(Node root) {
        root.midSeach(root);
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找该节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }


    /**
     * 删除节点
     *
     * @param value
     */
    public void deleteNode(int value) {
        if (root == null) {
            return;
        }
        //先查找到二叉树中对应的节点
        Node target = search(value);
        if (target == null) {
            return;
        }
        // 获取该节点的父节点
        Node parentNode = searchParent(value);
        //如果要删除的节点是叶子节点
        if (target.left == null && target.right == null) {
            // 删除的节点是左节点
            if (parentNode.left.value == value) {
                parentNode.left = null;
            } else {
                // 删除的节点是右节点
                parentNode.right = null;
            }
        } else if (target.left != null && target.right != null) {
            //删除的节点有两个子节点
            // 找到要删除节点(target)的前驱结点(左子树的最大值)或者后继节点(右子树的最小值),
            // 找到后将其删掉,并将其值于target进行替换
            // 获取目标节点的后置节点
            int min = searchMin(target.right);
            target.value = min;

        } else {// 删除的节点只有一个子节点
            // 删除的节点是左节点
            if (parentNode.left.value == value) {
                if (target.left!= null){
                    parentNode.left = target.left;
                }else{
                    parentNode.left = target.right;
                }
            } else {
                if (target.right!= null){
                    parentNode.right = target.right;
                }else{
                    parentNode.right = target.left;
                }
            }
        }

        return;
    }

    /**
     * 获取子树中的最小值
     * @param right
     * @return
     */
    private int searchMin(Node right) {
        Node target = right;
        while (target.left != null){
            target = target.left;
        }
        deleteNode(target.value);
        return target.value;
    }


}
