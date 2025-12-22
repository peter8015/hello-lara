package com.lara.subarray;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SubArraySumK 类的单元测试
 * 测试目标：public int subarraySum(int[] nums, int k)
 */
class SubArraySumKTest {

    private final SubArraySumK subArraySumK = new SubArraySumK();

    @Test
    @DisplayName("测试正常情况：存在多个符合条件的子数组")
    void testNormalCaseWithMultipleSubarrays() {
        // Given: 数组[1,1,1]，目标值2
        int[] nums = {1, 1, 1};
        int k = 2;
        // Expected: 存在两个子数组[1,1]满足条件
        int expected = 2;

        // When: 调用方法
        int result = subArraySumK.subarraySum1(nums, k);
//        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "应该找到两个和为2的子数组");
    }

    @Test
    @DisplayName("测试空数组情况")
    void testEmptyArray() {
        // Given: 空数组，目标值0
        int[] nums = {};
        int k = 0;
        // Expected: 没有子数组
        int expected = 0;

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "空数组应返回0");
    }

    @Test
    @DisplayName("测试单元素数组匹配目标值")
    void testSingleElementMatch() {
        // Given: 单元素数组，元素等于目标值
        int[] nums = {5};
        int k = 5;
        // Expected: 找到一个子数组
        int expected = 1;

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "单元素匹配应返回1");
    }

    @Test
    @DisplayName("测试单元素数组不匹配目标值")
    void testSingleElementNoMatch() {
        // Given: 单元素数组，元素不等于目标值
        int[] nums = {3};
        int k = 5;
        // Expected: 没有子数组满足条件
        int expected = 0;

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "单元素不匹配应返回0");
    }

    @Test
    @DisplayName("测试包含负数的情况")
    void testWithNegativeNumbers() {
        // Given: 包含负数的数组
        int[] nums = {1, -1, 0};
        int k = 0;
        // Expected: 子数组[-1,0]和[0]满足条件
        int expected = 3;

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "应正确处理负数并找到3个和为0的子数组");
    }

    @Test
    @DisplayName("测试所有元素相同且满足条件")
    void testAllElementsSameAndMatch() {
        // Given: 所有元素相同的数组
        int[] nums = {2, 2, 2};
        int k = 4;
        // Expected: 子数组[2,2]出现两次
        int expected = 2;

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "应找到两个和为4的子数组");
    }

    @Test
    @DisplayName("测试没有满足条件的子数组")
    void testNoMatchingSubarrays() {
        // Given: 数组中没有任何子数组满足条件
        int[] nums = {1, 2, 3};
        int k = 10;
        // Expected: 没有满足条件的子数组
        int expected = 0;

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "不应找到任何和为10的子数组");
    }

    @Test
    @DisplayName("测试大数组性能")
    void testLargeArrayPerformance() {
        // Given: 较大的数组
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = 1;
        }
        int k = 10;
        // Expected: 计算有多少个长度为10的连续子数组
        int expected = 91; // 从索引0到90，共91个长度为10的子数组

        // When: 调用方法
        int result = subArraySumK.subarraySum(nums, k);

        // Then: 验证结果
        assertEquals(expected, result, "应在大数组中正确计算满足条件的子数组数量");
    }
}
