package com.lara.dynamic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 139. word Break
 * Given a string s and a dictionary of strings wordDict, return true if
 * s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 * @author zhanghaibing
 * @date 2026-06-26
 */
public class WordBreakSolution {

    // time complexity: O(n^2)
    // space complexity: O(n)
    public boolean wordBreak1(String s, List<String> wordDict) {
        //check edge case
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        // convert list to set for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordDict);


        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // check if the substring from j to i is in the wordset
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        // check the edge cases
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        // convert list to set for lookup
        Set<String> words = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreakSolution solution = new WordBreakSolution();
        boolean result = solution.wordBreak("leetcode", List.of("leet", "code"));
        boolean result1 = solution.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"));
        System.out.println(result1);
        System.out.println(result);
    }
}
