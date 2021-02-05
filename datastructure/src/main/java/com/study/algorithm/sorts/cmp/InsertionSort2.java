package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName InsertSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/5 10:45
 * @Version 1.0
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int curIndex = i;
            T element = array[curIndex];
            while (curIndex > 0 && cmp(curIndex, curIndex - 1) < 0) {
                array[curIndex] = array[curIndex-1];
                curIndex--;
            }
            array[curIndex] = element;
        }
    }
}
