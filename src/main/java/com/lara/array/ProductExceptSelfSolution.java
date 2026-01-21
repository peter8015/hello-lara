package com.lara.array;

/**
 * leetcode238. Product of Array Except Self
 * Given an integer array nums, return an array answer such that answer[i]
 * is equals to the product of all the elements of nums except nums[i].
 * input: nums = [1,2,3,4]
 * output: [24,12,8,6]
 *
 * @author zhanghaibing
 * @date 2026-01-21
 */
public class ProductExceptSelfSolution {

    /**
     * 1. Can nums be null or empty?  Return empty array.
     * Can nums include one element? Return empty array.
     * 2. A navie approach is to calculate the product of all elements.
     * We can use nested loop, that would be O(n^2) time.
     * 3. We can use prefix and suffix array.
     *
     *
     * @param nums
     * @return
     */
   public int[] productExceptSelf(int[] nums) {
       // edge case check
       if(nums == null || nums.length == 0)  return new int[0];
       if(nums.length == 1) return new int[]{1};

       int n = nums.length;
       int[] result = new int[n];

       // use prefix and suffix array
       result[0] = 1;
       for(int i = 1; i < n; i++) {
           result[i] = result[i - 1] * nums[i - 1];
       }

       int right = 1;
       for(int i = n - 1; i >= 0; i--) {
           result[i] *= right;
           right *= nums[i];
       }
       return result;
   }

}
