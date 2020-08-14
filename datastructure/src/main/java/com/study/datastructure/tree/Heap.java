package com.study.datastructure.tree;

import java.util.Arrays;

/**
 * 二叉堆分为最大堆和最小堆，本质是完全二叉树
 * 最大堆是堆顶元素大于他的左孩子和右孩子
 * 最小堆是堆顶元素小于他的左孩子和右孩子
 * 堆存储在连续数组中，如果父节点的索引为parent
 * 左孩子为2*parent +1  右孩子为2*parent +2
 * 每次插入都是插入最后的叶子节点然后调整堆
 * 最后的叶子节点的索引为 (array.length -2)/2
 */
public class Heap {
    /**
     * 上浮调整，插入
     *
     * @param array
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        // 如果子节点的索引是偶数，表示有右节点，否则只有左节点
        int parentIndex = childIndex % 2 == 0 ? (childIndex - 2) / 2 : (childIndex - 1) / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = childIndex % 2 == 0 ? (childIndex - 2) / 2 : (childIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     *
     * @param array
     * @param parentIndex
     */
    public static void downAdjust(int[] array, int parentIndex) {

        int temp = array[parentIndex];
        int childIndex = getChild(array, parentIndex);
        while (childIndex < array.length && temp > array[childIndex]) {
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = getChild(array, parentIndex);
        }
        array[parentIndex] = temp;

    }

    /**
     * 根据父节点 获取子节点索引
     *
     * @param array
     * @param parentIndex
     * @return
     */
    public static int getChild(int[] array, int parentIndex) {
        int childIndex;
        int leftIndex = parentIndex * 2 + 1;

        if (leftIndex < array.length) {
            int rightIndex = parentIndex * 2 + 2;
            if (rightIndex >= array.length) {
                childIndex = leftIndex;
                return childIndex;
            }
            if (array[leftIndex] < array[rightIndex]) {
                childIndex = leftIndex;
            } else {
                childIndex = rightIndex;
            }
        } else {
            childIndex = leftIndex;
        }
        return childIndex;
    }

    /**
     * 构建堆
     *
     * @param array
     */
    public static void buildHeap(int[] array) {
        int index = (array.length - 1) % 2 == 0 ? (array.length - 3) / 2 : (array.length - 2) / 2;
        for (int i = index; i >= 0; i--) {
            downAdjust(array, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 9, 6, 5};
        System.out.println("上浮前数组：");
        System.out.println(Arrays.toString(arr));
        upAdjust(arr);
        System.out.println("上浮后数组：");
        System.out.println(Arrays.toString(arr));
        arr = new int[]{10, 3, 2, 6, 5, 7, 8, 9};
        System.out.println("下沉前数组：");
        System.out.println(Arrays.toString(arr));
        downAdjust(arr, 0);
        System.out.println("下沉后数组：");
        System.out.println(Arrays.toString(arr));
        arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        System.out.println("构建前数组：");
        System.out.println(Arrays.toString(arr));
        buildHeap(arr);
        System.out.println("构建后数组：");
        System.out.println(Arrays.toString(arr));
    }

}
