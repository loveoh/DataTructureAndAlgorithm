package com.study.leetcode.排序;

import java.util.Random;

import static java.util.Arrays.sort;

/**
 * @ClassName _75_颜色分类
 * @Description
 * @Author za-yaowei
 * @Date 2021/3/4 11:34
 * @Version 1.0
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _75_颜色分类 {


    /**
     * 使用双指针算法
     * 使用两个指针，p0 = 0，p1 = nums.length -1 ;
     * 循环遍历数组，nums[i] = 1;跳过
     *              nums[i] = 2 ; num[i] 和 nums[p1]交换元素，p1--；
     *              如果此时num[i] = 2 ; 接着和nums[p1] 交换元素。
     *              nums[i] = 0； nums[i] 和nums[p0]交换元素，p0++;
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int p0 = 0;
        int p1 = nums.length -1;

        // i指针小于p1就可以跳出循环
        for (int i = 0; i <= p1; i++) {
            int target = nums[i];
            while (i <= p1 && target == 2 ){
                nums[i] = nums[p1];
                nums[p1] = target;
                target = nums[i];
                p1--;
            }

            if (target == 0){
                nums[i] = nums[p0];
                nums[p0] = target;
                p0++;
            }
        }
    }






    /**
     * 使用快速排序
     * 时间复杂度 O(nlogn)
     *
     * @param nums
     */
    public  static void sortColors1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        sortColors(nums, 0, nums.length);


    }

    private static void sortColors(int[] nums, int startIndex, int endIndex) {
//        if (startIndex >= endIndex){
//            return;
//        }
        if ((endIndex - startIndex) < 2) return;
        int index = parition(nums, startIndex, endIndex);
        sortColors(nums, startIndex, index);
        sortColors(nums, index + 1 , endIndex);
    }

    private static int parition(int[] nums, int startIndex, int endIndex) {

        int  priovtIndex =startIndex +  (int) (Math.random() * (endIndex - startIndex));
        int priovt = nums[priovtIndex];
        swap(nums,startIndex,priovtIndex);
//        int left = startIndex;
//        int right = endIndex - 1;
        endIndex--;
        while (startIndex < endIndex){
            // 从右边开始遍历
            while (startIndex < endIndex){
                if (priovt < nums[endIndex]){
                    endIndex--;
                }else {
                    nums[startIndex++] = nums[endIndex];
                    break;
                }
            }
            // 开遍历左边
            while (startIndex < endIndex){
                if (priovt < nums[startIndex]){
                    nums[endIndex--] = nums[startIndex];
                    break;
                }else {
                    startIndex++;
                }
            }
        }
        nums[startIndex] = priovt;
        return startIndex;
    }

    private static void swap(int[] nums, int startIndex, int priovtIndex) {
        int temp = nums[startIndex];
        nums[startIndex] = nums[priovtIndex];
        nums[priovtIndex] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2,0,1};
        sortColors(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
