package com.lara.dynamic;

/**
 *
 * leetcode 198
 * Problem: Given an array of non-negative integers representing houses with money,
 * rob the maximum amount without robbing two adjacent houses.
 * <p>
 * input: [1, 2, 3, 1]
 * output: 4
 *
 * @author zhanghaibing
 * @date 2026-06-22
 */
public class HouseRobberSolution {

    // time complexity : O(n)
    // space complexity : O(1)
    public int robx(int[] nums) {
        // prev1 represents max profit up to the previous house
        int prev1 = 0, prev2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]); // make a decision: rob or not
            // use slide window to understand this, move one step to right (---, prev2<prev1>, prev1<curr>)
            prev2 = prev1;
            prev1 = curr;  // store the best result to prev1
        }
        return prev1;
    }

    // time complexity : O(n)
    // space complexity : O(1)
    public int rob(int[] nums) {
        // edge check
        if(nums == null || nums.length == 0) return 0;

        int prev1 = 0, prev2 = 0;
        for(int i = 0; i < nums.length; i++) {
            // decide rob or not
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        HouseRobberSolution solution = new HouseRobberSolution();
        int result = solution.rob(new int[]{1, 2, 3, 1, 7});
        System.out.println(result); //11

    }
}


/**
Step 1 — Clarify
"So we can't rob two adjacent houses, and we want to maximize the total. No edge cases to worry about?"

Step 2 — Approach
"This is a DP problem. At each house, I either skip it or rob it plus the best from two steps back. I only need two variables, not an array."

Step 3 — Walk the code
"prev1 is the best so far. prev2 is the best before that. Each step I pick the larger of — skip, or rob this house plus prev2."


Step 4 — Complexity
"Time is O(n), one pass. Space is O(1), just two variables."


One-liner to close:
"Single pass, constant space — clean and optimal."
 **/