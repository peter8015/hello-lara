package com.lara.array;

/**
 * leetcode53. 53. Maximum Subarray
 * Given an integer of array nums, find the subarray with the largest sum, and return its sum.
 * Subarray is a contiguous and non-empty sequence elements within array.
 *
 *  Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * output: 6
 *
 * @author zhanghaibing
 * @date 2025-12-22
 */
public class MaximumSubarraySolution {
    /**
     * os:
     * @param nums
     * @return
     */
    public int maximumSubarrayx(int[] nums) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums can not be null");
        }
        // boundary check
        if(nums.length == 1) return nums[0];

        // kadens algorithm
        int maxSum = nums[0];
        int currSum = nums[0];

        for(int i = 0; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }


    public int maximumSubarray(int[] nums) {
        // boundary check
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must be not null");
        }

        if(nums.length == 1) return nums[0];

        // kaden's algorithm
        int maxSum = nums[0];
        int currSum = nums[0];

        for(int i = 0; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}
