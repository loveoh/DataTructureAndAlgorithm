package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName selectionSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 16:58
 * @Version 1.0
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 快速排序
     */
    @Override
    protected void sort() {
        sort(0, array.length);

    }

    private void sort(int begin, int end) {
        if ((end - begin) < 2) return;
        int pivotIndex = pivotIndex(begin, end);
        sort(begin, pivotIndex);
        sort(pivotIndex + 1, end);

    }

    private int pivotIndex(int begin, int end) {
        // 每次都随机获取基准元素的位置。然后赋值给begin
        swap(begin, (int) (begin + Math.random() * (end - begin)));


        // 获取基准元素
        T pivot = array[begin];
//        int left = begin;
//        int right = end;
        end--;
        while (begin < end) {
            while (begin < end) {
                /**
                 * 从右边开始比较
                 * 右边的元素大于pivot，end--
                 * 右边的元素小于pivot，右边元素的位置和左边指针当前元素的位置互换，并且end--
                 */
                if (cmp(pivot, array[end]) < 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        // 退出循环时，begin==end; 把备份出来的基准元素放入到begin索引位置上
        array[begin] = pivot;
        return begin;
    }
}
