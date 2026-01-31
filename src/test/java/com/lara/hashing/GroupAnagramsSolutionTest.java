package com.lara.hashing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;


/**
 *
 * @author zhanghaibing
 * @date 2026-01-31
 */
public class GroupAnagramsSolutionTest {

    @Test
    public void testGroupAnagrams() {
        GroupAnagramsSolution solution = new GroupAnagramsSolution();

        // 测试用例1: 基本功能测试
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = Arrays.asList(
                Arrays.asList("eat", "tea", "ate"),
                Arrays.asList("tan", "nat"),
                Arrays.asList("bat")
        );
        List<List<String>> result1 = solution.groupAnagrams(strs1);
        assertEquals(expected1.size(), result1.size());
        assertContainsAllGroups(expected1, result1);

        // 测试用例2: 空数组
        String[] strs2 = {};
        List<List<String>> expected2 = Arrays.asList();
        List<List<String>> result2 = solution.groupAnagrams(strs2);
        assertEquals(expected2.size(), result2.size());

        // 测试用例3: 单个元素
        String[] strs3 = {"a"};
        List<List<String>> expected3 = Arrays.asList(Arrays.asList("a"));
        List<List<String>> result3 = solution.groupAnagrams(strs3);
        assertEquals(expected3.size(), result3.size());
        assertContainsAllGroups(expected3, result3);

        // 测试用例4: 所有字符串都是anagram
        String[] strs4 = {"abc", "bca", "cab"};
        List<List<String>> expected4 = Arrays.asList(Arrays.asList("abc", "bca", "cab"));
        List<List<String>> result4 = solution.groupAnagrams(strs4);
        assertEquals(expected4.size(), result4.size());
        assertContainsAllGroups(expected4, result4);

        // 测试用例5: 没有anagram
        String[] strs5 = {"abc", "def", "ghi"};
        List<List<String>> expected5 = Arrays.asList(
                Arrays.asList("abc"),
                Arrays.asList("def"),
                Arrays.asList("ghi")
        );
        List<List<String>> result5 = solution.groupAnagrams(strs5);
        assertEquals(expected5.size(), result5.size());
        assertContainsAllGroups(expected5, result5);
    }

    /**
     * 辅助方法：验证结果是否包含了所有期望的组（不考虑顺序）
     */
    private void assertContainsAllGroups(List<List<String>> expected, List<List<String>> actual) {
        assertEquals(expected.size(), actual.size());

        for (List<String> expectedGroup : expected) {
            boolean found = false;
            for (List<String> actualGroup : actual) {
                if (areListsEqualIgnoreOrder(expectedGroup, actualGroup)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Expected group not found in actual result: " + expectedGroup);
        }
    }

    /**
     * 辅助方法：判断两个列表是否包含相同的元素（忽略顺序）
     */
    private boolean areListsEqualIgnoreOrder(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        List<String> copy1 = new java.util.ArrayList<>(list1);
        List<String> copy2 = new java.util.ArrayList<>(list2);

        for (String item : copy1) {
            if (!copy2.contains(item)) {
                return false;
            }
            copy2.remove(item);
        }

        return copy2.isEmpty();
    }

}