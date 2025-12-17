package com.lara.dubblepoints;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;


/**
 * @author zhanghaibing
 * @date 2024-02-12
 */
public class Longest {

    private static Logger LoggerFactory;
    private static final Logger log = Logger.getLogger(String.valueOf(Longest.class));

    /**
     * leetcode 3 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 思想：这个算法主要是维护一个滑动窗口，窗口内的所有字符都是不重复的。当遇到重复的字符时，缩小
     * 窗口的左边界直到重复字符移出窗口，然后继续向右扩展窗口并更新最大长度。这样可以遍历一次字符串
     * 的前提下找到无重复的最大长串。
     * <p>
     * 解题思路：
     * <p>
     * 时间复杂度分析：O(n), 其中n为字符长度。
     * <p>
     * 易错点：
     * 1. sets.remove(s.charAt(left));
     * 2. 对比完成再进行移动
     * max = Math.max(max, right - left + 1);
     * right++;
     * <p>
     * [deliberate practice]
     * time: 3:16
     */
    private int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // dubble points
        int left = 0, right = 0, len = 0, maxLen = 0;
        Set<Character> sets = new HashSet();

        while (right < s.length()) {
            char c = s.charAt(right);
            if (!sets.contains(c)) {
                sets.add(c);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            } else {
                sets.remove(s.charAt(left));
                left++;
            }
        }
        return maxLen;
    }

    @Test
    public void test() {
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void test2() {
        assertEquals(5, longest2("abcabcbbdefg"));
    }

    public int longest2(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0, max = 0;
        Set<Character> sets = new HashSet();

        while(right < s.length()) {
            char c = s.charAt(right);
            if(sets.contains(c)) {
                sets.remove(s.charAt(left));
                left++;
            } else {
                sets.add(c);
                max = Math.max(max, right - left + 1);
                right++;
            }
        }
        return max;
    }
}











