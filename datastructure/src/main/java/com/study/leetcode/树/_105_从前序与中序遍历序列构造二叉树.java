package com.study.leetcode.树;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _105_从前序与中序遍历序列构造二叉树 {

    static Map<Integer,Integer> nodeMap = new HashMap<>();
    static int[] pre ;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            nodeMap.put(inorder[i], i);
        }
        pre = preorder;
        TreeNode root = buildTree(0,preorder.length-1,0,inorder.length-1);
        return root;
    }

    /**
     *
     * @param ps 前序遍历，左子树或右子树的起始位置，
     * @param pe  前序遍历，左子树或右子树的结束位置，
     * @param is   中序遍历，左子树或右子树的起始位置，
     * @param ie   中序遍历，左子树或右子树的结束位置，
     * @return
     */
    static TreeNode  buildTree(int ps, int pe, int is, int ie) {
        if (ps > pe || is > ie){
            return null;
        }
        // 找到root节点的值
        int value = pre[ps];
        TreeNode node = new TreeNode(value);
        // 获取root节点在中序遍历中的位置
        int ir = nodeMap.get(value);
        // 计算好每次递归调用时，左子树，右子树的节点起始位置
        node.left =  buildTree(ps +1 ,ps + ir -is ,is,ir-1);
        node.right = buildTree(ps + ir -is +1,pe,ir+1,ie);
        return node;
    }

    public static void main(String[] args) throws ParseException {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        buildTree(preorder,inorder);

        System.out.println(getMonthBetween("2020-11-11","2020-12-23"));


    }



    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        min = null;max = null;curr = null;
        return result;
    }
}
