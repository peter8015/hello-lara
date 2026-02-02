package com.lara.hashing;

import java.util.*;

/**
 * leetcode49 Group Anagrams
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * input:  ["eat","tea","tan","ate","nat","bat"]
 * output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * @author zhanghaibing
 * @date 2026-01-31
 */
public class GroupAnagramsSolution {

    // time complexity O(klogk)
    // space complexity O(nk)
    public List<List<String>> groupAnagramsx(String[] strs) {
        // handle edge cases
        if(strs == null || strs.length == 0) return new ArrayList<>();

        // use map to store groups
        Map<String, List<String>> map = new HashMap<>();

        for(String s : strs) {
            // convert string to char array for sorting
            char[] cs = s.toCharArray();
            Arrays.sort(cs); // sort the chars
            String key = String.valueOf(cs);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
//            if(!map.containsKey(key)) {
//                map.put(key, new ArrayList<>());
//            }
//            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }

    public List<List<String>> groupAnagrams(String[] anagrams) {

        // handle edge cases
        if (anagrams == null || anagrams.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        // use map to store
        for (String s : anagrams) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);  // O(klogk)
            String key = String.valueOf(cs);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}
