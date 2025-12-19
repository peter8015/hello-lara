package com.lara.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Give an array of integers nums and an integer k,
 * return the total number of subarrays whose sum equals to k.
 *
 * @author zhanghaibing
 * @date 2025-12-18
 */
public class SubArraySumK {
    public int subarraySum(int[] nums, int k) {

        int count = 0, preSum = 0;
        // key: 前缀和, value: 该前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 基础情况：前缀和刚好等于 k

        for (int num : nums) {
            preSum += num;
            // 如果之前存在一个前缀和等于 preSum - k
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    // Bruth force O(n^2)
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            int sum = 0;
            for(int j = i; j < nums.length; j++) {
                sum += nums[j];
                if(sum == k) count++;
            }
        }
        return count;
    }



}


