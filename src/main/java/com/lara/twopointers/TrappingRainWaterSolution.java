package com.lara.twopointers;


/**
 * leetcode42 Trapping Rain Water
 * You are given an integer array height of length n. There are n
 * vertical lines drawn such that the two endpoints of the i line are
 * (i, 0) and (i, height[i])
 * <p>
 * input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * output: 6
 * <p>
 * time complexity: O(n)
 * space complexity: O(1)
 *
 *     // Two-pointer approach to solve the trapping rain water problem
 *     // Core idea: For each position, the trapped water depends on the minimum of left max height and right max height
 *     // Use left and right pointers moving towards each other, dynamically maintaining max heights on both sides
 *
 * @author zhanghaibing
 * @date 2025-04-02
 */
public class TrappingRainWaterSolution {

    // Two-pointer approach to solve the trapping rain water problem
    // time complexity: O(n) space complexity: O(1)
    public int trappingRainWaterx(int[] height) {
        // TODO: Handle potential integer overflow in result calculation for very large inputs
        // TODO: Implement alternative solutions (e.g., dynamic programming) for educational purposes

        // Boundary condition check: if the array is null or has fewer than 3 elements, no water can be trapped
        if (height == null || height.length < 3) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;

        // Move the two pointers toward each other to calculate trapped water at each position
        while (left < right) {
            // If the left height is smaller, process from the left side
            if (height[left] < height[right]) {
                // Update the maximum height on the left
                leftMax = Math.max(leftMax, height[left]);
                // Accumulate the trapped water at the current position
                result += leftMax - height[left];
                left++;// Move the left pointer to the right
            } else {
                rightMax = Math.max(rightMax, height[right]);
                result += rightMax - height[right];
                right--;
            }
        }
        return result;  // Return the total amount of trapped water
    }

    // dry run the code
    // time complexit: O(n) space complexity:O(1)
    public int trappingRainWater(int[] height) {
        // edge cases check
        if(height == null || height.length < 3) return 0;

        // use two-pointer
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int result = 0;

        // move two pointers toward each other
        while(left < right) {
            if(height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                result += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}