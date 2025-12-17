package com.lara.slidingwindow;

import java.util.HashSet;

/**
 *
 *
 * leetcode
 * @author zhanghaibing
 * @date 2025-03-12
 */
public class LongestSubstringSolution {


    /**
     * 1. Clarity the problem.
     * - if s == null or length == 0
     * 2. Analyze with examples
     * - abcd, 4
     * - abca, 3
     * - aaa, 3
     * 3. Design the algorithm.
     * - brute force
     * - sliding window
     *
     * 4. Write code.
     * - valid check
     * - left, right,set
     *
     * 5. Test and debug.
     * - cases
     *
     * 6. Follow-up discussion.
     * - refactor:  readability, extensibility, performance, maintainability
     * - extract method base TDD
     * - rate limit solution
     *
     *
     *
     *
     * time complexity: O(n)
     * space complexity: O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // valid check
        if(s == null || s.length() == 0) {
            return 0;
        }

        if(s.length() == 1) {
            return 1;
        }

        var sets = new HashSet<Character>();
        int left = 0;
        int maxlen = 0;

        for(int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // Remove the duplicate elements
            while(sets.contains(c)) {
                sets.remove(s.charAt(left));
                left++;
            }

            sets.add(c);

            // Update the max
            maxlen = Math.max(maxlen, right - left + 1);
        }
        return maxlen;
    }



    public int lengthOfLongestSubString(String s) {
        return 0;

    }





















}
