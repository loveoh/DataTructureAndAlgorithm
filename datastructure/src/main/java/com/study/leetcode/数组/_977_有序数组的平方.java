package com.study.leetcode.数组;

/**
 * @ClassName _977_有序数组的平方
 * @Description
 * @Author za-yaowei
 * @Date 2021/3/5 12:54
 * @Version 1.0
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _977_有序数组的平方 {
    public  static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int[] target = new int[nums.length];
        int left = 0;
        int right = nums.length -1;
        int cur = nums.length -1;
        while (left <= right){
            int leftNum = (int) Math.pow(nums[left] ,2);
            int rightNum = (int) Math.pow(nums[right],2);
            if (leftNum > rightNum){
                left++;
                target[cur--] = leftNum;
            }else {
                right--;
                target[cur--] = rightNum;
            }

        }
        return target;
    }

    public static void main(String[] args) {
        int[] nums = {-7,-3,2,3,11};
        int[] nums1 =sortedSquares(nums);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }
}
