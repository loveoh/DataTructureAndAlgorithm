package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * @ClassName SimpleSort
 * @Description 基础排序算法
 * @Author za-yaowei
 * @Date 2020/7/15 9:38
 * @Version 1.0
 */
public class SimpleSort {

    /**
     * 选择排序
     * 假定数组中第一个数为最小元素，遍历后续数组元素并进行比较，如果后续元素比最小元素小，则交换下标。
     * 一直找到最小的元素，然后和第一个元素进行交换元素。循环往复，直到整个数组完全排序。
     *
     * @param nums
     * @return
     */
    public static int[] selectSort(int[] nums) {

        int a = nums.length;
        for (int i = 0; i < a; i++) {
            int min = i;
            for (int j = i + 1; j < a; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[min];
            nums[min] = temp;
        }
        return nums;
    }

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
     * @param nums
     * @return
     */
    public static int[] insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 当前的需要比较的元素
            int current = nums[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current < nums[preIndex]) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
        }
        return nums;
    }

    /**
     * 冒泡排序
     *
     * @param nums
     */
    public static int[] bubboSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 希尔排序
     *
     * @param nums
     * @return
     */
    public static int[] shellSort(int[] nums) {
        int gap = nums.length / 2;

        while (gap > 0) {
            for (int i = gap; i < nums.length; i++) {
                int preIndex = i - gap;
                int temp = nums[i];
                while (preIndex >= 0 && nums[preIndex] > temp) {
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;

                }
                nums[preIndex + gap] = temp;
            }
            gap = gap / 2;
        }
        return nums;
    }

    /**
     * 归并排序
     * 将一个大小为n的集合，将集合进行逐层的折半分组，
     * 第一层分为2个大组，每组元素为n/2个
     * 第二层分为4个大组，每组元素n/4个
     * 。。。。
     * 直到每组都只有一个元素
     * 将小组之间进行排序，然后归并成一个大数组。
     * 循环往复 直到结束排序
     *
     * @param nums  要排序的数组
     * @param start 要排序数组的起始位置
     * @param end   要排序数组的结束位置
     */
    public static void mergeSort(int[] nums, int start, int end) {

        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    public static void merge(int[] nums, int start, int mid, int end) {
        //创建一个额外临时数组，大小是另外两个数组的长度之和。用来临时存放排序后的数组
        int[] tempArray = new int[end - start + 1];
        // 左边数组的指针
        int p1 = start;
        //右边数组的指针
        int p2 = mid + 1;
        // 临时数组的指针
        int p = 0;
        while (p1 <= mid && p2 <= end) {
            // 左边元素小，就把该元素放入临时数组中，p1++  p++
            if (nums[p1] < nums[p2]) {
                tempArray[p] = nums[p1];
                p1++;
            } else {
                tempArray[p] = nums[p2];
                p2++;
            }
            p++;
        }
        while (p1 <= mid) {
            tempArray[p] = nums[p1];
            p1++;
            p++;
        }
        while (p2 <= end) {
            tempArray[p] = nums[p2];
            p2++;
            p++;
        }
        for (int i = 0; i < tempArray.length; i++) {
            nums[i + start] = tempArray[i];
        }
    }

    public static void quickSort(int[] arr , int startIndex ,int endIndex){

        if (startIndex >= endIndex){
            return;
        }

        int index = partition(arr,startIndex,endIndex);
        quickSort(arr,startIndex,index-1);
        quickSort(arr,index + 1,endIndex);
    }


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


    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 8, 1, 9, 22, 545, 78, 12,0};
//        int[] nums = {7, 4, 1};
//        int[] retsult = SimpleSort.selectSort(nums);
//        int[] retsult = SimpleSort.insertSort(nums);
//        int[] retsult = SimpleSort.bubboSort(nums);
//        int[] retsult = SimpleSort.shellSort(nums);
//       SimpleSort.merge(nums,0,2,nums.length-1);`
//        SimpleSort.mergeSort(nums, 0, nums.length - 1);
        SimpleSort.quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(retsult));

    }


}
