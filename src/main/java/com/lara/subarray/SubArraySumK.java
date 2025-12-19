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
    // O(n)
    public int subarraySum(int[] nums, int k) {
        // key:sum value:count
        Map<Integer, Integer> data = new HashMap();
        data.put(0, 1);

        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (data.containsKey(sum - k)) {  //!
                count += data.get(sum - k);
            }
            data.put(sum, data.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // Bruth force O(n^2)
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }
}


