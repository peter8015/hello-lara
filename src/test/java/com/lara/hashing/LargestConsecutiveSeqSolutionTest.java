package com.lara.hashing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class LargestConsecutiveSeqSolutionTest {
    
    private LargestConsecutiveSeqSolution solution;
    
    @BeforeEach
    void setUp() {
        solution = new LargestConsecutiveSeqSolution();
    }
    
    /**
     * 测试空数组情况
     */
    @Test
    public void testNullArray() {
        int result = solution.longestConsecutiveSeq(null);
        assertEquals(0, result, "当输入为null时，应返回0");
    }
    
    /**
     * 测试空数组情况
     */
    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(0, result, "当输入为空数组时，应返回0");
    }
    
    /**
     * 测试单个元素数组
     */
    @Test
    public void testSingleElement() {
        int[] nums = {1};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(1, result, "单个元素的数组，最长连续序列长度应为1");
    }
    
    /**
     * 测试连续序列 [1,2,3,4,5]，期望结果为5
     */
    @Test
    public void testSimpleConsecutiveSequence() {
        int[] nums = {1, 2, 3, 4, 5};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(5, result, "连续序列[1,2,3,4,5]的最长连续序列长度应为5");
    }
    
    /**
     * 测试包含重复元素的连续序列 [1,2,2,3,4]，期望结果为4
     */
    @Test
    public void testConsecutiveWithDuplicates() {
        int[] nums = {1, 2, 2, 3, 4};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(4, result, "包含重复元素的连续序列[1,2,2,3,4]的最长连续序列长度应为4");
    }
    
    /**
     * 测试复杂序列 [100,4,200,1,3,2]，期望结果为4 (序列: 1,2,3,4)
     */
    @Test
    public void testComplexSequence() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(4, result, "复杂序列[100,4,200,1,3,2]的最长连续序列长度应为4");
    }
    
    /**
     * 测试多个不连续序列 [9,1,4,7,3,-1,0,5,8,-1,6]，期望结果为9 (序列: -1,0,1,2,3,4,5,6,7,8)
     */

    @Test
    public void testMultipleSequences() {
        int[] nums = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        int result = solution.longestConsecutiveSeq(nums);
        // 实际上最长连续序列是 -1,0,1,2,3,4,5,6,7,8 (长度为10) 或者 3,4,5,6,7,8 (长度为6)，取决于是否有2
        // 但是数组中没有2，所以最长连续序列是 -1,0,1,3,4,5,6,7,8 中断了（缺少2）
        // 实际最长序列是 3,4,5,6,7,8,9 (长度为7) 或 -1,0,1 (长度为3)
        // 等等，让我重新计算：排序去重后是 -1,0,1,3,4,5,6,7,8,9
        // 连续序列有：-1,0,1 (长度3)，3,4,5,6,7,8,9 (长度7)
        // 所以最长连续序列长度应该是7
        assertEquals(7, result, "多序列[9,1,4,7,3,-1,0,5,8,-1,6]的最长连续序列长度应为7");
    }
    
    /**
     * 测试负数序列 [-3,-2,-1,0,1]，期望结果为5
     */
    @Test
    public void testNegativeNumbers() {
        int[] nums = {-3, -2, -1, 0, 1};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(5, result, "负数序列[-3,-2,-1,0,1]的最长连续序列长度应为5");
    }
    
    /**
     * 测试无连续序列的数组 [1,3,5,7,9]，期望结果为1
     */
    @Test
    public void testNoConsecutiveSequence() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(1, result, "无连续序列的数组[1,3,5,7,9]的最长连续序列长度应为1");
    }
    
    /**
     * 测试相同元素数组 [1,1,1,1]，期望结果为1
     */
    @Test
    public void testAllSameElements() {
        int[] nums = {1, 1, 1, 1};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(1, result, "所有元素相同的数组[1,1,1,1]的最长连续序列长度应为1");
    }
    
    /**
     * 测试大范围序列 [0,1,2,4,5,6,7,8,9]，期望结果为6 (序列: 4,5,6,7,8,9)
     */
    @Test
    public void testLargeRangeSequence() {
        int[] nums = {0, 1, 2, 4, 5, 6, 7, 8, 9};
        int result = solution.longestConsecutiveSeq(nums);
        assertEquals(6, result, "大范围序列[0,1,2,4,5,6,7,8,9]的最长连续序列长度应为6");
    }
}
