package com.lara.arrayandlinkedlist;

import org.junit.Test;

/**
 * @author zhanghaibing
 * @date 2024-02-28
 */

public class MoveZero {

    /**
     * 283.移动零
     * 题目：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 解题思路：双指针
     * 1. 遍历数组，当fast指向元素不为0时，与slow指针交换位置。
     * 2. 当fast指针指向的元素为0时，fast指针向后移动，直到找到下一个非0元素
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

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};

        moveToZero1(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
