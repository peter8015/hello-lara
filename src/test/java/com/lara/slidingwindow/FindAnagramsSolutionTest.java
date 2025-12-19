package com.lara.slidingwindow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * {@link FindAnagramsSolution#findAnagram} 的单元测试类
 */
class FindAnagramsSolutionTest {

    private final FindAnagramsSolution solution = new FindAnagramsSolution();

    @Test
    @DisplayName("基本功能测试 - 存在多个匹配项")
    void testBasicFunctionalityWithMultipleMatches() {
        // Given: 给定源字符串和目标字符串
        String s = "abab";
        String p = "ab";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 验证结果正确性
        List<Integer> expectedResult = Arrays.asList(0, 2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("边界情况 - 源字符串为空")
    void testEdgeCaseSourceEmptyString() {
        // Given: 源字符串为空
        String s = "";
        String p = "abc";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 结果应为空列表
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("边界情况 - 目标字符串为空")
    void testEdgeCaseTargetEmptyString() {
        // Given: 目标字符串为空
        String s = "abc";
        String p = "";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 结果应为空列表
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("特殊输入 - 目标字符串比源字符串长")
    void testSpecialInputTargetLongerThanSource() {
        // Given: 目标字符串长度大于源字符串
        String s = "abc";
        String p = "abcd";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 结果应为空列表
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("全部匹配 - 整个字符串都是目标异位词")
    void testAllMatchEntireStringIsAnagram() {
        // Given: 整个源字符串就是目标字符串的一个排列
        String s = "abc";
        String p = "bca";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 应找到一个匹配项，起始索引为0
        List<Integer> expectedResult = Arrays.asList(0);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("无匹配项")
    void testNoMatchingAnagrams() {
        // Given: 源字符串中不存在目标字符串的异位词
        String s = "abcdefg";
        String p = "xyz";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 结果应为空列表
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("包含重复字符的情况")
    void testRepeatedCharactersInStrings() {
        // Given: 源字符串和目标字符串都包含重复字符
        String s = "aaab";
        String p = "aab";

        // When: 执行查找异位词方法
        List<Integer> actualResult = solution.findAnagram(s, p);

        // Then: 应从索引1开始有一个匹配项
        List<Integer> expectedResult = Arrays.asList(1);
        assertEquals(expectedResult, actualResult);
    }
}
