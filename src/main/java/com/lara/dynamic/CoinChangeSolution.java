package com.lara.dynamic;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 *  示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 解题思路：
 * 采用自上而下的思路，定义F(i)为组成金额，i为最少硬币的数量。假设在计算F(i)时，
 * 已经计算出F(0) - F(i - 1), 则F(i)对应的转移方程式为：
 * dp[i] = min(dp[i], dp[i - coin[j]] +  1)
 *
 * @author zhanghaibing
 * @date 2024-03-19
 */
public class CoinChangeSolution {

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        // 遍历目标金额和硬币，如果对于每个目标金额i，如果硬币小于i，可以尝试更新dp[i]，
        // 在已知dp[i - coin[j]]的最小硬币上加上1
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= 1) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 判断能否凑出目标金额
        return dp[amount] > amount ? -1 : dp[amount];
    }


    @Test
    public void test() {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = coinChange(coins, amount);
        System.out.println(result);

        assertEquals(amount, result);
    }
}
// k, [1,3,5,7.....]