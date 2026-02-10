package com.lara.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode3 Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without duplicate characters.
 * 
 * input: s = "abcabcbb"
 * output: 3
 * 
 * @author zhanghaibing
 * @date 2025-03-12
 */
public class LongestSubstringWithoutRepeatingSolution {

    // time complexity: O(n)
    // space complexity:O(n)
    public int longestSubstringWithoutRepeatingx(String s) {
        // boundary check
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;

        Set<Character> sets = new HashSet<>();
        char[] cs = s.toCharArray();
        int left = 0, maxLen = 0;

        for(int right = 0; right < cs.length; right++) {
            while(sets.contains(cs[right])) {
                sets.remove(cs[right]);
                left++;
            }
            sets.add(cs[right]);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public int longestSubstringWithoutRepeating(String s) {
        // edge cases check
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;

        // use set to store
        char[] cs = s.toCharArray();
        Set<Character> sets = new HashSet<>();
        int left = 0, maxLen = 0;

        for(int right = 0; right < cs.length; right++) {
            while(sets.contains(cs[right])) {
                sets.remove(cs[right]);
                left++;
            }
            sets.add(cs[right]);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
