package com.lara.greedy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * MaxProfitSolution 单元测试类
 * 测试函数：public int maxProfit(int[] prices)
 *
 * @author zhanghaibing
 * @date 2025-08-26
 */
public class MaxProfitSolutionTest {

    private MaxProfitSolution maxProfitSolution;

    @BeforeEach
    public void setUp() {
        maxProfitSolution = new MaxProfitSolution();
    }

    /**
     * 测试用例：prices 为 null，期望返回 0
     */
    @Test
    public void testMaxProfit_NullInput_ReturnsZero() {
        int[] prices = null;
        int result = maxProfitSolution.maxProfit(prices);
        assertEquals(0, result, "当输入为 null 时，应返回 0");
    }

    /**
     * 测试用例：prices 为空数组，期望返回 0
     */
    @Test
    public void testMaxProfit_EmptyArray_ReturnsZero() {
        int[] prices = {};
        int result = maxProfitSolution.maxProfit(prices);
        assertEquals(0, result, "当输入为空数组时，应返回 0");
    }

    /**
     * 测试用例：prices 只有一个元素，无法交易，期望返回 0
     */
    @Test
    public void testMaxProfit_SingleElement_ReturnsZero() {
        int[] prices = {5};
        int result = maxProfitSolution.maxProfit(prices);
        assertEquals(0, result, "当只有一个价格时，无法交易，应返回 0");
    }

    /**
     * 测试用例：prices 递增数组，利润为最大值减最小值
     */
    @Test
    public void testMaxProfit_IncreasingPrices_ReturnsMaxProfit() {
        int[] prices = {1, 2, 3, 4, 5};
        int result = maxProfitSolution.maxProfit(prices);
        assertEquals(4, result, "递增数组中最大利润应为 5 - 1 = 4");
    }

    /**
     * 测试用例：prices 递减数组，无利润可得
     */
    @Test
    public void testMaxProfit_DecreasingPrices_ReturnsZero() {
        int[] prices = {5, 4, 3, 2, 1};
        int result = maxProfitSolution.maxProfit(prices);
        assertEquals(0, result, "递减数组中无利润可得，应返回 0");
    }

    /**
     * 测试用例：prices 为一般波动数组，期望返回最大利润
     */
    @Test
    public void testMaxProfit_NormalFluctuation_ReturnsCorrectProfit() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = maxProfitSolution.maxProfit(prices);
        assertEquals(5, result, "正常波动数组中最大利润应为 6 - 1 = 5");
    }

    /**
     * 测试用例：所有元素相同，无利润
     */
    @Test
    public void testMaxProfit_AllSamePrices_ReturnsZero() {
        int[] prices = {3, 3, 3, 3};
        int result = maxProfitSolution.maxProfit3(prices);
        assertEquals(0, result, "所有价格相同，无利润，应返回 0");
    }
}
