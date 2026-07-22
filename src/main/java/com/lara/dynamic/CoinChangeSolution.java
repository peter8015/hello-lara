package com.lara.dynamic;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 解题思路：
 * 采用自上而下的思路，定义F(i)为组成金额，i为最少硬币的数量。假设在计算F(i)时，
 * 已经计算出F(0) - F(i - 1), 则F(i)对应的转移方程式为：
 * dp[i] = min(dp[i], dp[i - coin[j]] +  1)
 *
 * @author zhanghaibing
 * @date 2024-03-19
 */
public class CoinChangeSolution {


    // time complexity: O(n * amount)
    // space complexity: O(amount)
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //from 0

        // Initialize all the value with 'amount + 1', a number larger than any possible valid coin count
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
        }

        for (int money = 1; money <= amount; money++) {
            for (int coin : coins) {
                if (money >= coin) {
                    // dp status: compare the original minimum coins and the count using current coin
                    dp[money] = Math.min(dp[money], dp[money - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    // time complexity: O(n * amount)
    // space complexity: O(amount)
    public int coinChange(int[] coins, int amount) {
        // check edge case
        if(coins == null) return 0;
        if(amount < 0) return -1;
        if(amount == 0) return 0;

        // use array to store the result
        int[]  dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int money = 1; money <= amount; money++) {
            for(int coin : coins) {
                if(money >= coin) {
                    // compare the original minimum coins and the count using current coin
                    dp[money] = Math.min(dp[money], dp[money - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChangeSolution sol = new CoinChangeSolution();
        System.out.println(sol.coinChange(new int[]{1, 3, 5}, 11)); // Output 3
        System.out.println(sol.coinChange(new int[]{2}, 3));       // Output -1
    }
}
