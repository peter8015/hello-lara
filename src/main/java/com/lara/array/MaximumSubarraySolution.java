package com.lara.array;

/**
 * leetcode53. Given an integer of array nums, find the subarray with the largest sum, and return its sum
 * // Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * // output 6
 *
 * @author zhanghaibing
 * @date 2025-12-22
 */
public class MaximumSubarraySolution {
    // Kadane's algorithm, O(n)
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int largest = nums[0];
        int currentSum = nums[0];

        // thought: double iterator, find the subarray with largest sum
        for(int i = 0; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            largest = Math.max(largest, currentSum);
        }

        return largest;
    }

    // bruth-force, O(n^2)
    public int maximumSubArray(int[] nums) {
        int largest = nums[0];

        for(int i = 0; i < nums.length; i++) {
            int sum = 0;
            for(int j = i; j < nums.length; j++) {
                sum += nums[j];
                if(largest < sum) {
                    largest = sum;
                }
            }
        }
        return largest;
    }
}
