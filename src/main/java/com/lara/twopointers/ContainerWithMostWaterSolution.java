package com.lara.twopointers;

/**
 * leetcode11. Container With Most Water
 * medium
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of
 * the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * @author zhanghaibing
 * @date 2026-02-02
 */
public class ContainerWithMostWaterSolution {

    // O(n), O(1)
    public int containerWithMostWater(int[] height) {
        // handle boundary check
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(area, maxArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }


}
