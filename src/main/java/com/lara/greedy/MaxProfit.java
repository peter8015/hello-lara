package com.lara.greedy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhanghaibing
 * @date 2024-02-04
 */
public class MaxProfit {

    /**
     * leetcode 121 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择某一天买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 解题思路：
     * 1. 利用贪心算法，每天进行买卖，记录当前的最小值和最大利润。
     * 2. DP
     * 时间复杂度分析：O(n)
     * <p>
     * 易错点：
     * 1. minPrice设置为最大值，如果为0，那么在第一个元素为0时，minPrice永远为0，永远无法得到利润
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int minPrice = Integer.MAX_VALUE;

        if (prices == null) {
            return 0;
        }

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (profit < prices[i] - minPrice) {
                profit = prices[i] - minPrice;
            }
        }
        return profit;
    }

    public int maxProfile2(int[] prices) {
        int profit = 0;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            } else if(profit < prices[i] - min) {
                profit = prices[i] - min;
            }
        }

        return profit;
    }



    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 4};

        assertEquals(5, maxProfit(prices));
    }
}






