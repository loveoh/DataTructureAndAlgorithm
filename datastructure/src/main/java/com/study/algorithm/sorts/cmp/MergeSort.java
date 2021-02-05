package com.study.algorithm.sorts.cmp;

import com.study.algorithm.sorts.Sort;

/**
 * @ClassName selectionSort
 * @Description
 * @Author za-yaowei
 * @Date 2021/2/3 16:58
 * @Version 1.0
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    private T[] leftArray;


    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length / 2];
        sort(0, array.length);

    }

    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) / 2;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }



    /**
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     */
//    private void merge(int begin, int mid, int end) {
//        int li = 0, le = mid - begin;
//        int ri = mid, re = end;
//        int ai = begin;
//
//        // 备份左边数组
//        for (int i = li; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//
//        // 如果左边还没有结束
//        while (li < le) {
//            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }


    private void merge(int begin, int mid, int end) {
        // 用来备份左边数组的起始索引
        int li = 0;
        // 备份左边数组的结束索引
        int le = mid - begin;
        // 右半部分数组的起始索引
        int ri = mid;
        // 右半部分数组的结束索引
        int re = end;
        // 原数组需要比较合并的索引位置
        int ai = begin;

        // 将要合并的数组的左半部分，放入到备份数组中
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        // 左边的数组比较完之后，整个归并排序结束
        while (li < le) {
            if (ri < re && cmp( array[ri],leftArray[li] ) < 0) {
                array[ai++] = array[ri++];
//                ai++;
//                ri++;
            } else {
                array[ai++] =  leftArray[li++];
//                ai++;
//                li++;
            }
        }
    }
}
