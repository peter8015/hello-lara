package com.lara.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode128. Longest Consecutive Sequence
 * Given an integer array nums, return the largest length of the consecutive sequence elements.
 * You must write an algorithm that runs in O(n) time.
 *
 * input: nums = [100,4,200,1,3,2]
 * output: 4
 *
 *  @author zhanghaibing
 * @date 2026-01-23
 */
public class LargestConsecutiveSeqSolution {

    public int longestConsecutiveSeq(int[] nums) {
        // edge cases handling
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        Set<Integer> numberSet = new HashSet();
        for (int num : nums) {
            numberSet.add(num);
        }

        int maxLen = 0;
        for (int num : numberSet) {
            if (!numberSet.contains(num - 1)) {
                int currNum = num;
                int currLen = 1;

                while (numberSet.contains(currNum + 1)) {
                    currLen++;
                    currNum++;
                }
                maxLen = Math.max(currLen, maxLen);
            }
        }
        return maxLen;

    }
}