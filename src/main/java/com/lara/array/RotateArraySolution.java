package com.lara.array;

/**
 * leetcode198 Rotate Array
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * @author zhanghaibing
 * @date 2026-01-03
 */
public class RotateArraySolution {

    // O(n) time and O(1) space
    public void rotate(int[] nums, int k) {
        // edge case check
        if(nums == null || nums.length <= 1) return;

        k %= nums.length;
        if(k == 0) return; // if k ends up being 0 after modulo, no need to rotate.

        // triple reverse
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    // colliding two pointers
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // breth force: O(n*k) time and O(n) space
    public void rotate2(int[] nums, int k) {
        // check
        if(nums == null || nums.length <= 1) return;

        int count = 0;
        int[] first = new int[nums.length];
        k %= nums.length; // crucial logic: k > nums.length

        for(int i = nums.length - k; i < nums.length; i++) {
            first[count] = nums[i];
            count++;
        }

        for(int i = 0; i < nums.length - k; i++) {
            first[k + i] = nums[i];
        }

        System.arraycopy(first, 0, nums, 0, first.length);
    }





}


