package com.lara.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Todo:
 * 1. problem description
 * 2. CAD-WTF
 *
 * leetcode15
 * Problem:
 * Give an integer array, return all the triplets [nums[i], nums[j], nums[k]],
 * such that i != j, j != k, and i != k，and [nums[i] + nums[j] + nums[k]] == 0.
 * Notice triplets are not duplicate.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * @author zhanghaibing
 * @date 2024-02-23
 *
 */
public class ThreeSumSolution {

    /**
     * CAD-WTF: Clarity, Analyze, Design, Write, Test, Follow-up
     * <p>
     * Clarity: Questions
     *
     * Design: sort+two pointer+ duplicate handling
     * 1. Sort the array to use two pointers.
     * 2. Iterate through the array.
     * 3. Use two pointer approach.
     * (Avoid duplicate triplets)
     * Time complexity:O(n^2) (big O of n squared)
     * Space complexity: O(1)
     * <p>
     * Follow-up: Optimal
     * 1. Use Hashing for a Potential O(n²) Alternative
     * Instead of a two-pointer approach after sorting, we can use a hash set
     * to check for the third value in O(1) time.
     * Advantage: Avoids redundant checks and simplifies duplicate handling.
     * Trade-off: Requires additional space (O(n)) for the hash set.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Edge case check
        if (nums == null || nums.length < 3) return result;

        // Sort the array to use tow-pointer approach
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move pointers to avoid duplicate triplets
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        // edge check
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        // use two points
        for (int i = 0; i < nums.length - 2; i++) {
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }


    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        //edge check
        if(nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        // use two points
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
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

    // [2,4,1,5,1,1]
    // time complexity: O(N^2)
    public List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        // edge check
        if(nums == null || nums.length < 3) return result;

        // sort array
        Arrays.sort(nums);

        // use two pointers
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // remove duplicate
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

    // Give an integer array nums, find all the triplets.
    public List<List<Integer>> threeSum5(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        // edge check
        if(nums == null || nums.length < 3) return result;

        // sort array
        Arrays.sort(nums);

        // use two pointers
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while(left < right) {
              int sum = nums[i] + nums[left] + nums[right];

              if(sum == 0) {
                  result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                  // remove duplicates
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
