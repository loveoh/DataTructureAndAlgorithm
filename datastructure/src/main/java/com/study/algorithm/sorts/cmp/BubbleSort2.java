package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName BubbleSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 14:29
 * @Version 1.0
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;
            for (int begin = 1; begin < array.length - i; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    flag = false;
                }
            }
            if (flag) break;
        }
    }
}
