package com.lara.array;

/**
 * leetcode198 Rotate Array
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * os:
 * 1. clarify inputs and outpus
 * - Can k be larger than the length of the array?
 * - Should I modify the array in place or is returning a new array can be acceptable?
 * - If the array is null or has one element, should I return the same array?
 * 2. Discuss the bruth force and optimal solutions
 * - A navie approach would be create a new array and copy elements from the original array. That would be O(n) time and O(n) space.
 * - A better approach would be to use a triple reverse. That would be O(n) time and O(1) space.
 * video: https://www.youtube.com/watch?v=Fwu9UmV1LJs&t=62s
 * 3. write clean code with os
 * 4. complexity analysis
 * 5. bottle neck or production environment
 *
 * @author zhanghaibing
 * @date 2026-01-03
 */
public class RotateArraySolution {
    // O(n) time and O(1) space
    public void rotate(int[] nums, int k) {
        /**os:I'm adding a guard clause here to handle null or empty inputs early,
         * which keeps the core logic clean and ensures the function is robust.
         */
        // boundary check
        if (nums == null || nums.length <= 1) return;

        /**
         * os:I'll normalize $k$ to handle cases where it exceeds the array size,
         * ensuring we only perform the necessary rotations.
         */
        k %= nums.length;
        if (k == 0) return; // if k ends up being 0 after modulo, no need to rotate.

        /**
         * os: I'll rotate the array by flipping the global order,
         * then restoring the local order of the two partitions.
         * Reverse the entire array.
         * Reverse the first $k$ elements.
         * Reverse the remaining $n-k$ elements.
         */
        // triple reverse
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // time O(n) space O(1)
    public void rotatex(int[] nums, int k) {
        // edge case check
        if (nums == null || nums.length <= 1) return;

        // k > nums.length
        k %= nums.length;

        // triple reverse
        reversex(nums, 0, nums.length - 1);
        reversex(nums, 0, k - 1);
        reversex(nums, k, nums.length - 1);
    }

    public void reversex(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // A navie approach would be rotating array one time, k times. That would be O(nk), which is inefficient.
    // bruth force O(nk)
    public void rotateBruth(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        k %= n; // 优化：如果 k > n，取余数即可，避免无意义的循环

        // 外层循环：控制移动的次数 k
        for (int i = 0; i < k; i++) {
            // 每次移动一位的逻辑
            int last = nums[n - 1]; // 1. 先保存数组的最后一个元素

            // 2. 将数组中前面的元素逐个向后挪动一位
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            // 3. 将原先的末尾元素放到数组开头
            nums[0] = last;
        }
    }
}




