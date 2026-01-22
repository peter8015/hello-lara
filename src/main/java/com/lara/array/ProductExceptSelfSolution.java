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

   public int[] productExceptSelfx(int[] nums) {
       // edge case check
       if(nums == null || nums.length == 0)  return new int[0];

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


    /**
     * Give an integer array nums, return the product of the elements except nums[i].
     *
     * intput: nums = [1,2,3,4]
     * output: [24,12,8,6]
     *
     */
    public int[] productExceptSelf(int[] nums) {
        // edge case check
        if(nums == null || nums.length == 0) return new int[0];
        if(nums.length == 1) return new int[1];

        int n = nums.length;
        int[] result = new int[n];

        // use prefix and suffix
        result[0] = 1;
        for(int i = 0; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int right = 1;
        for(int i = n - 1; i > 0; i--) {
            result[i] *= right;
            right *= nums[i];

        }
        return result;
    }
}
