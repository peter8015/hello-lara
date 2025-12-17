package com.lara.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: Given an array of integers nums and an integer target,
 * return indices of two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution.
 *
 * 1. Clarity the problem.
 * - Ask for confirm the detail:
 * Is there array "nums" given here sorted?
 * Does the array given here contain duplicate elements?
 * Is it possible that the variable "target" given here is empty?
 * Are there any special constrains here? Such as time/space complexity?
 * Could the input array contain negative numbers?
 * Do we need to handle duplicate results?
 *
 * 2. Analyze with the examples.
 * - Manually simulate simple cases
 * - Discuss the boundary conditions.
 *
 * @author zhanghaibing
 * @date 2025-03-11
 *
 */
public class TwoSumSolution {
    public int[] twoSum(int[] nums, int target) {
        // Valid input: Must contain two numbers.
        if (nums == null || nums.length < 2) {
            return new int[0]; // Return empty array instead of throwing an exception.
        }

        // HashMap to store nubmers and their corresponding indices.
        var map = new HashMap<Integer, Integer>(); // Use var for better readability

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if complement exist in the map.
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            //Avoid overwriting duplicate values
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }

        // Return empty array if no solution if found.
        return new int[0];
    }

    // [1,2,3,4,5] target = 9, [3, 4]
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        // edge check
        if(nums == null || nums.length == 0) return result;

        // use hashmap
        Map<Integer, Integer> data = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            int b = target - nums[i];

            if(data.containsKey(b)) {
                result[0] = data.get(b);
                result[1] = i;
            }
            data.put(nums[i], i);
        }
        return result;
    }

    public int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];
        //edge check
        if(nums == null || nums.length == 0) return result;

        // use hashmap
        Map<Integer, Integer> data = new HashMap();

        for(int i = 0; i < nums.length; i++) {
            int b = target - nums[i];

            if(data.containsKey(b)) {
                result[0] = data.get(b);
                result[1] = i;
            }
            data.put(nums[i], i);
        }
        return result;
    }


    public int[] twoSum4(int[] nums, int target) {
        int[] result = new int[2];

        // edge check
        if(nums == null || nums.length == 0) return result;

        // use hashmap   [1,2,3,4] 7
        Map<Integer, Integer> data = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if(data.containsKey(b)) {
                result[0] = data.get(b);
                result[1] = i;
            }
            data.put(nums[i], i);
        }
        return result;
    }
}
















