package com.lara.arrayandlinkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhanghaibing
 * @date 2024-02-23
 */
public class ThreeSum {

    /**
     leetcode 15. 三数之和
     题目：
     给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
     同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。

     注意：答案中不可以包含重复的三元组。

     思路：排序+双指针法+去重处理
     1. 排序：利用快速排序对数组进行排序。
     2. 双指针遍历：目标为三数之和sum，先固定一个数据nums[i]，再设置两个指针，分别指向该数的下一个元素left = i + 1，
     和数组的末尾元素right = nums.length - 1;
     - 当sum小于0时，则需要增加sum，左指针向右移动一位
     - 当sum大于0时，刚需要减小sum，右指针向左移动一位
     3. 去重处理：移动左右指针避免重复
     时间复杂度：O(n^2)
     */
    List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 对输入数组进行排序，方便后续用双指针法进行查找
        Arrays.sort(nums);

        // 从左到右遍历数组，利用双指针法，找出三元组。遍历到倒数第三个，因为需要三个数相加为0
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 这里分别移动左右指针，避免有重复的元素导致重复的组合
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

}
