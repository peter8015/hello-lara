package com.lara.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode15. 3Sum
 * Give an integer array, return all the triplets [nums[i], nums[j], nums[k]],
 * such that i != j, j != k, and i != k，and [nums[i] + nums[j] + nums[k]] == 0.
 * Notice triplets are not duplicate.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * input: nums = [-1, 0, 1, 2, -1, -4]    -4 -1 -1 0 1 2
 * output: [[-1, -1, 2], [-1, 0, 1]]
 *
 * @author zhanghaibing
 * @date 2024-02-23
 *
 */
public class ThreeSumSolution {

    /**
     * time complexity: O(n^2)
     * space complexity: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // edge cases check  todo Edge case: 3Sum needs at least 3 elements
        if(nums == null || nums.length < 3) return new ArrayList<>();

        // sort array to avoid dumplicates
        Arrays.sort(nums);

        // use two pointers
        for(int i = 0; i < nums.length - 2; i++) {   // todo i = 1  nums.length - 2
            // todo Since the array is sorted, if my current number is greater than zero,
            // there's no way the remaining numbers will sum to zero. So I can just break early to save time."  [-1,0, 1,2,4]
            if(nums[i] > 0) break;

            //todo skipping duplicates for i to keep the result set unique.
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;  //todo left = i + 1
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // eliminate duplicates
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
