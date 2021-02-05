package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName InsertSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/5 10:45
 * @Version 1.0
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {

    /**
     * 插入排序
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5。
     * <p>
     * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
     *
     * @return
     */
    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int curIndex = i;
            while (curIndex > 0 && cmp(curIndex, curIndex - 1) < 0) {
                swap(curIndex,curIndex-1);
                curIndex--;
            }
        }
    }
}
