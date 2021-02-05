package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName selectionSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 16:58
 * @Version 1.0
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    /**
     *  选择排序：
     *  在序列中找到最大的元素，然后与数组的最后一个位置交换位置。
     *  执行完之后。最末尾的位置就是最大的值。
     *  以此类推
     */
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            // 假设最大的元素在0位置。
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, max) > 0) {
                    // 把当前索引位置赋值给max
                    max = begin;
                }
            }
            // 把最大值，放到当前数组的最后一个位置
            swap(end,max);
        }
    }
}
