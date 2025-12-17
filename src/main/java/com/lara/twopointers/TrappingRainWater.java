package com.lara.twopointers;


/**
 * Questions remaining:
 * 1. problem description
 * 2. What's the clarity questions.
 * 3. Watch the algorithm video.

 * 1. problem description:
 * You are given an integer array height of length n. There are n
 * vertical lines drawn such that the two endpoints of the i line are
 * (i, 0) and (i, height[i])
 *
 * @author zhanghaibing
 * @date 2025-04-02
 */
public class TrappingRainWater {
    /**
     * CAD-WTF: Clarity, Analyze, Design, Write, Test, Follow-up
     *
     * Clarity: Questions
     * 1. Are there any constraints on the input?
     * 2. Are there any negative values?
     * 3. Is the input sorted?
     *
     * Design: ideas
     * 1. Two pointer approach: place one pointer at the left, and another at the right of the array.
     * 2. Trace the max height: keep trace the maximum from the left(leftMax) and from the right(rightMax).
     * 3. Move the smaller pointer(optional):
     * If the height at the left pointer is smaller than the height at the right pointer, calculate trapped water based on leftMax, then move the left pointer to the right.
     * If the height at the right pointer is smaller or equal, calculate trapped water based on rightMax, then move the right pointer to the left.
     *
     * 1. Time Complexity is O(n). The algorithm uses two pointers (left and right) that traverse the array at most once.
     * 2. Space Complexity is O(1). The algorithm only use some variables: left,leftMax.
     *
     * @param height
     * @return
     */
    public int trapWater(int[] height) {
        if (height == null || height.length < 2) return 0;

        int l = 0, r = height.length - 1;
        int max = 0;

        while (l < r) {
            int left = height[l], right = height[r];
            int width = r - l;
            int area = 0;

            area = (int) Math.min((long) left, (long) right) * width;
            if (area > max) max = area;

            if (left < right) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
