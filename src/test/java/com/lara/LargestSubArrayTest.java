package com.lara;

/**
 *
 * @author zhanghaibing
 * @date 2025-12-22
 */

import com.lara.array.LargestSubArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LargestSubArrayTest {

    private final LargestSubArray solution = new LargestSubArray();

    @Test
    @DisplayName("标准示例测试：混合正负数")
    void testStandardExample() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, solution.maxSubArray(nums), "应返回子数组 [4,-1,2,1] 的和");
    }

    @Test
    @DisplayName("边缘情况：单元素数组")
    void testSingleElement() {
        assertEquals(1, solution.maxSubArray(new int[]{1}));
        assertEquals(-5, solution.maxSubArray(new int[]{-5}));
    }

    @Test
    @DisplayName("边缘情况：全负数数组")
    void testAllNegativeElements() {
        int[] nums = {-5, -1, -8, -3};
        assertEquals(-1, solution.maxSubArray(nums), "全负数时应返回最大的那个负数");
    }

    @Test
    @DisplayName("包含零的情况")
    void testIncludingZero() {
        assertEquals(3, solution.maxSubArray(new int[]{0, 1, 2, -1}));
        assertEquals(0, solution.maxSubArray(new int[]{-1, -2, 0}));
    }

    @Test
    @DisplayName("异常处理：空值检测")
    void testExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> solution.maxSubArray(null));
        assertThrows(IllegalArgumentException.class, () -> solution.maxSubArray(new int[]{}));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("参数化测试：多种数组组合")
    void testWithParameters(int[] nums, int expected) {
        assertEquals(expected, solution.maxSubArray(nums));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(new int[]{5, 4, -1, 7, 8}, 23),
                Arguments.of(new int[]{1, 2, 3, 4}, 10),
                Arguments.of(new int[]{-1, -2, -3}, -1)
        );
    }
}
