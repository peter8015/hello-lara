package com.lara.array;

import com.lara.array.ProductExceptSelfSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ProductExceptSelfSolution类的单元测试
 * 测试productExceptSelf方法的各种边界条件和正常情况
 */
public class ProductExceptSelfSolutionTest {

    private ProductExceptSelfSolution solution;

    @BeforeEach
    public void setUp() {
        solution = new ProductExceptSelfSolution();
    }

    /**
     * 测试输入为null的情况
     * 预期返回空数组
     */
    @Test
    public void testNullInput() {
        int[] result = solution.productExceptSelf(null);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * 测试空数组输入
     * 预期返回空数组
     */
    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * 测试单元素数组
     * 预期返回空数组
     */
    @Test
    public void testSingleElementArray() {
        int[] nums = {5};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * 测试两个元素的数组
     * 验证基本功能是否正确
     */
    @Test
    public void testTwoElements() {
        int[] nums = {1, 2};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0]); // 除了第一个元素，其他元素的乘积: 2
        assertEquals(1, result[1]); // 除了第二个元素，其他元素的乘积: 1
    }

    /**
     * 测试三个元素的数组 [1,2,3]
     * 预期结果应该是 [6,3,2]
     */
    @Test
    public void testThreeElements() {
        int[] nums = {1, 2, 3};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(6, result[0]); // 2*3=6
        assertEquals(3, result[1]); // 1*3=3
        assertEquals(2, result[2]); // 1*2=2
    }

    /**
     * 测试包含零的数组 [1,0,3]
     * 预期结果应该是 [0,3,0]
     */
    @Test
    public void testArrayWithZero() {
        int[] nums = {1, 0, 3};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(0, result[0]); // 0*3=0
        assertEquals(3, result[1]); // 1*3=3
        assertEquals(0, result[2]); // 1*0=0
    }

    /**
     * 测试包含多个零的数组 [0,0,3]
     * 预期结果应该是 [0,0,0]
     */
    @Test
    public void testArrayWithMultipleZeros() {
        int[] nums = {0, 0, 3};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(0, result[0]); // 0*3=0
        assertEquals(0, result[1]); // 0*3=0
        assertEquals(0, result[2]); // 0*0=0
    }

    /**
     * 测试包含负数的数组 [-1,1,0,3]
     * 验证负数处理是否正确
     */
    @Test
    public void testArrayWithNegativeNumbers() {
        int[] nums = {-1, 1, 0, 3};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(4, result.length);
        assertEquals(0, result[0]); // 1*0*3=0
        assertEquals(0, result[1]); // -1*0*3=0
        assertEquals(-3, result[2]); // -1*1*3=-3 -> 实际上应该是 -3
        assertEquals(0, result[3]); // -1*1*0=0
    }

    /**
     * 测试更大的数组 [2,3,4,5]
     * 预期结果应该是 [60,40,30,24]
     */
    @Test
    public void testLargerArray() {
        int[] nums = {2, 3, 4, 5};
        int[] result = solution.productExceptSelf(nums);
        assertNotNull(result);
        assertEquals(4, result.length);
        assertEquals(60, result[0]); // 3*4*5=60
        assertEquals(40, result[1]); // 2*4*5=40
        assertEquals(30, result[2]); // 2*3*5=30
        assertEquals(24, result[3]); // 2*3*4=24
    }
}
