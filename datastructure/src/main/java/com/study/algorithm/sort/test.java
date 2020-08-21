package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * @ClassName test
 * @Description
 * @Author za-yaowei
 * @Date 2020/8/19 9:38
 * @Version 1.0
 */
public class test {

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int index = partition1(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);
    }

    private static int partition1(int[] arr, int start, int end) {
        int privet = arr[start];
        int index = start;
        int left = start;
        int right = end;
        while (right >= left) {
            while (right >= left) {
                if (arr[right] < privet) {
                    arr[index] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            while (right >= left) {
                if (arr[left] > privet) {
                    arr[index] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = privet;
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {23, 3, 5, 6, 2, 4, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
