package com.lara.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 算法思路：
 * 1. 首先，我们需要创建一个哈希表来存储目标异位词的字符频率。
 * 2. 接下来，我们使用一个滑动窗口在字符串s上移动，每次移动一个字符的长度。
 * 3. 在每次移动窗口时，我们检查窗口中的子串是否是目标异位词。我们可以通过更新另一个哈希表来比较窗口内子串的字符频率是否与目标异位词一致。
 * 4. 如果是异位词，我们记录窗口左侧的索引，作为一个有效的起始索引。
 * 5. 继续滑动窗口，直到遍历完整个字符串s。
 *
 * 时间复杂度分析：
 *     时间复杂度为O(n) ，其中n为字符串s的长度。
 *
 * @author zhanghaibing
 * @date 2024-04-12
 */
public class FindAnagramsSolution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }

        // 创建目标异位词的哈希表来存储字符频率
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // 创建滑动窗口的哈希表
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0, right = 0;
        int match = 0; // 记录窗口内字符与目标异位词字符相符的数量

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            // 处理窗口右侧字符
            if (targetMap.containsKey(rightChar)) {
                windowMap.put(rightChar, windowMap.getOrDefault(rightChar, 0) + 1);
                if (windowMap.get(rightChar).equals(targetMap.get(rightChar))) {
                    match++; // 如果窗口内字符频率与目标字符频率一致，match加一
                }
            }
            right++; // 右侧指针右移

            // 窗口大小达到目标异位词长度时，进行窗口内字符匹配
            while (right - left == p.length()) {
                if (match == targetMap.size()) {
                    result.add(left); // 如果匹配，记录窗口左侧索引
                }
                char leftChar = s.charAt(left);
                // 处理窗口左侧字符
                if (targetMap.containsKey(leftChar)) {
                    windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                    if (windowMap.get(leftChar) < targetMap.get(leftChar)) {
                        match--; // 如果窗口内字符频率小于目标字符频率，match减一
                    }
                }
                left++; // 左侧指针右移
            }
        }

        return result;
    }


    // 1. 创建一个哈希表存储字符出现的频率
    // 2. 遍历字符串s，每次移动一个字符的长度
    // 3. 记录窗口内字符的频率
    // 4. 如果窗口内字符的频率与目标字符的频率一致，则记录窗口左侧索引
    // 5. 继续移动窗口，直到遍历完整个字符串s
    // 6. 返回所有有效的起始索引
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList();
        if(s == null || p == null || s.length() == 0 || p.length() == 0) {
            return result;
        }

        return null;
    }

    public static void main(String[] args) {
        FindAnagramsSolution solution = new FindAnagramsSolution();
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = solution.findAnagrams2(s, p);
        System.out.println(result);  // 输出：[0, 6]
    }
}



