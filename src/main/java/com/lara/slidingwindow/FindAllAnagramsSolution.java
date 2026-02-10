package com.lara.slidingwindow;

import java.util.*;

/**
 * 438. Find All Anagrams in a String
 * medium [i]
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * @author zhanghaibing
 * @date 2024-04-12
 */
public class FindAllAnagramsSolution {
    public List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        //1. create frequency arrays(size 26 for 'a' to 'z')
        int[] target = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) {
            target[c - 'a']++;
        }

        //2. slide the 'right' pointer across string s
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            window[s.charAt(right) - 'a']++;
            //3. maintain window size
            if (right - left + 1 > p.length()) {
                window[s.charAt(left) - 'a']--;
                left++;
            }

            //4. check if the current window match the target
            if (Arrays.equals(target, window)) {
                result.add(left);
            }
        }
        return result;
    }
}



