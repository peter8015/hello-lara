package com.lara;

/**
 *
 * @author zhanghaibing
 * @date 2025-12-22
 */

import com.lara.array.MaximumSubarraySolution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MaximumSubarraySolutionTest {

    private final MaximumSubarraySolution solution = new MaximumSubarraySolution();

    @Test
    @DisplayName("标准示例测试：混合正负数")
    void testStandardExample() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, solution.maximumSubarray(nums), "应返回子数组 [4,-1,2,1] 的和");
    }

    @Test
    @DisplayName("边缘情况：单元素数组")
    void testSingleElement() {
        assertEquals(1, solution.maximumSubarray(new int[]{1}));
        assertEquals(-5, solution.maximumSubarray(new int[]{-5}));
    }

    @Test
    @DisplayName("边缘情况：全负数数组")
    void testAllNegativeElements() {
        int[] nums = {-5, -1, -8, -3};
        assertEquals(-1, solution.maximumSubarray(nums), "全负数时应返回最大的那个负数");
    }

    @Test
    @DisplayName("包含零的情况")
    void testIncludingZero() {
        assertEquals(3, solution.maximumSubarray(new int[]{0, 1, 2, -1}));
        assertEquals(0, solution.maximumSubarray(new int[]{-1, -2, 0}));
    }

    @Test
    @DisplayName("异常处理：空值检测")
    void testExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> solution.maximumSubarray(null));
        assertThrows(IllegalArgumentException.class, () -> solution.maximumSubarray(new int[]{}));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("参数化测试：多种数组组合")
    void testWithParameters(int[] nums, int expected) {
        assertEquals(expected, solution.maximumSubarray(nums));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(new int[]{5, 4, -1, 7, 8}, 23),
                Arguments.of(new int[]{1, 2, 3, 4}, 10),
                Arguments.of(new int[]{-1, -2, -3}, -1)
        );
    }
}
