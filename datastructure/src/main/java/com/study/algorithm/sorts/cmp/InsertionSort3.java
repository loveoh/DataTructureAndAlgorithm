package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName InsertSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/5 10:45
 * @Version 1.0
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

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
        // 使用二分法进行优化
        for (int i = 1; i < array.length; i++) {
            int destIndex = search(i);
            T v = array[i];
            // 将destIndex之后的元素往后挪一位
            for (int index = i;index > destIndex ; index--){
                array[index] = array[index-1];
            }
            // 插入当前元素到合适的位置
            array[destIndex] = v;
        }
    }


    // 获取到插入排序中，当前索引的元素，要插入到前半部分已排好序的数组中的索引位置
    private int search(int i) {
        int begin = 0;
        int end = i;
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (cmp(i, mid) > 0) {
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }


    public static int binarySearch(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int min = (begin + end) / 2;
            if (v == array[min]) {
                return min;
            } else if (v > array[min]) {
                begin = min + 1;
            } else {
                end = min;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 8, 16, 17, 24, 35};
        System.out.println(binarySearch(array, 352));
    }
}
