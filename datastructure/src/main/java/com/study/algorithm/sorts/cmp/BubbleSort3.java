package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName BubbleSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 14:29
 * @Version 1.0
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int end = array.length -1; end > 0; end--) {
            // 记录最后一个需要交换元素的索引位置
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            // 该索引位置后的元素都是有序的，不需要再进行比较
            end = sortedIndex;
        }
    }
}
