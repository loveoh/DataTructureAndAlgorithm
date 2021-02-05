package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName selectionSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 16:58
 * @Version 1.0
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    // 用来保存创建出来堆的大小
    private int heapSize;
    /**
     * 堆排序
     * 1、首先批量建堆，创建一个大顶堆。
     * 2、交换数组的第一个元素和最后一个元素，然后进行下滤操作。
     * 一次操作之后，数组的尾部就是最大的值
     * 重复上述操作，直到数组完全有序
     */
    @Override
    protected void sort() {
        // 先创建出来一个大顶堆
         heapSize = array.length;
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
        // 交换数组第一个元素和最后一个元素
        while (heapSize > 1) {
            swap(0, --heapSize);
            // 调整大顶堆
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        T element = array[index];
        while (index < heapSize / 2) {
            int childIndex = 2 * index + 1;
            T child = array[childIndex];
            int rightChildIndex = childIndex + 1 ;
            if (rightChildIndex < heapSize && cmp(childIndex, rightChildIndex) < 0) {
                childIndex = rightChildIndex;
                child = array[childIndex];
            }
            if (cmp(child, element) <= 0) break;

            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
