package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Description
 * @Author za-yaowei
 * @Date 2020/8/17 10:05
 * @Version 1.0
 */
public class QuickSort {

    /**
     *  快速排序的核心思想：
     *      通过分治算法，将一个大的数组分成两个小数组，在每次拆分的时候，选取一个基准值，然后将元素与基准值比较，
     *      比基准值元素大的元素，放在基准值右边，比基准值小的元素，放在基准值左边。一直拆分到不能拆分为止。
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort(int[] arr , int startIndex ,int endIndex){
        if (startIndex >= endIndex){
            return;
        }
        int index = partition1(arr,startIndex,endIndex);
        quickSort(arr,startIndex,index-1);
        quickSort(arr,index + 1,endIndex);
    }

    /**
     * 挖坑法：
     *  选定基准元素pivot，并记录基准元素的位置index，这个位置相当于“坑”，设置两个指针left和right
     *  分别指向数组的第一个元素和最后一个元素。
     *  从right指针开始，如果arr[right] > arr[index]，right指针向左移动一位，如果arr[right] < arr[index]，
     *  把right所指向的元素填入坑中，坑的位置变成right所在的位置。且left向右移动一位。
     *  切换到left指针进行比较，如果arr[left] < arr[index] ,left指针向右移动一位。如果arr[left] > arr[index],
     *  left所指向的元素填入坑中，坑的位置变成left所在的位置，且right向右移动一位。
     *  以此类推，当所有的小于pivot的元素都在index的左边，所有大于pivot的元素都在index的右边。
     */
    public static int partition(int[] arr ,int startIndex , int endIndex){
        // 选取第一个元素为基准元素
        int pivot = arr[startIndex];
        // 数组左边指针
        int left = startIndex;
        // 数组右边指针
        int right = endIndex;
        //坑位，初始等于startIndex的位置
        int index = startIndex;

        while (right >= left){
            while (right >= left){
                // 右指针
                if (arr[right] < pivot){
                    arr[index] = arr[right];
                    index = right;
                    left ++;
                    break;
                }
                right--;
            }

            while (right >= left){
                if (arr[left] > pivot){
                    arr[index] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    /**
     *  指针交换：
     *      首先选取一个基准元素，pivot,左右两个指针 left和right，分别指向数组的第一个元素和最后一个元素。
     *      开始第一轮循环，首先从right指针开始，arr[right] > pivot ，则指针向左移动，如果arr[right] < pivot；则right停止移动，
     *      切换到left指针，arr[left] <= pivot 则left向右移动，如果arr[left] > pivot,则left指针停止移动，让left和right所指向的
     *      元素进行交换，第一轮循环结束。接着开始第二轮循环，以此类推
     *      如果left和right相重合，我们让pivot和left、right相重合的元素进行交换。
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static int partition1(int[] arr ,int startIndex , int endIndex){

        int left = startIndex;
        int right = endIndex;
        int pivot = arr[startIndex];
        while (left != right){
            // 操作右指针
            while (right > left && arr[right] >= pivot){
                right--;
            }
            // 操作左指针
            while (right > left && arr[left] <= pivot){
                left++;
            }
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        int temp = pivot;
        arr[startIndex] = arr[left];
        arr[left] = temp;
        return  left;

    }


    public static void main(String[] args) {
        int[] arr = {23, 3, 5, 6, 2, 4, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
