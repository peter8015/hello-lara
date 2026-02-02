package com.lara.arrayandlinkedlist;

import org.junit.Test;

/**
 *
 * leetcode 283. Move Zeroes
 * easy
 * Give an Integer array nums, move all the zeroes to the end of it while maintaining the relative order of the non-zero elements.
 * input: [0,1,0,3,12]
 * output: [1,3,12,0,0]
 *
 * @author zhanghaibing
 * @date 2024-02-28
 */

public class MoveZeroesSolution {

    /**
     * 283.移动零
     * 题目：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 解题思路：双指针
     * 1. 遍历数组，当fast指向元素不为0时，与slow指针交换位置。
     * 2. 当fast指针指向的元素为0时，fast指针向后移动，直到找到下一个非0元素
     * example:
     * 想象你面前有一排饮料瓶（这就是数组 nums），里面有的是满的（非零数字），有的是空的（数字 0）。
     * 你的任务是：把满的瓶子全部推到左边靠紧，空的瓶子留在右边。 但你不能拿个新托盘（不能开新数组），只能在这一排里倒腾。
     */
    public void moveToZero1(int[] nums) {
        int slow = 0;

        for(int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] != 0) {
                if(slow != fast) {
                    int temp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = temp;
                }
                slow++;
            }
        }
    }


    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length < 2) return;

        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] != 0) {
                if(nums[fast] != nums[slow]) {
                    int temp = nums[slow];
                    nums[slow] = nums[fast];
                    nums[fast] = temp;
                }
                slow++;
            }
        }
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};

//        moveToZero1(nums);
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
