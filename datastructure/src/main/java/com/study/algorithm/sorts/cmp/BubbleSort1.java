package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName BubbleSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 14:29
 * @Version 1.0
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int end = array.length -1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
