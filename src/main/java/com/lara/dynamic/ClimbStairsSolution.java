package com.lara.dynamic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 *
 * leetcode 70. 爬楼梯 简单
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * 解题思路：由于子问题存在重叠（即到达某一层的方法依赖于到达前一层或前二层的方法），可以使用动态规划法避免重复计算。
 * 定义状态dp[n]表示到达n阶楼梯的方法数。
 * - 基础情况：
 *  - 当n = 0时，已经在楼顶，所以dp[0]=1种方法。
 *  - 当n = 1时，需要走1步，所以有dp[1]=1种方法。
 * - 状态转移方程：
 *  - 当n > 1时，到达第n阶的方法数等于到达第n-1阶和n-2阶的方法数之和，即dp[n]=dp[n -1] + dp[n - 2].
 *
 *  解法一：递归，时间复杂度为O(n^2)
 *  解法二：记录递归，时间复杂度为O(n)
 *  解法三：动态规划，时间复杂度为O(n)
 * @author zhanghaibing
 * @date 2024-03-19
 */
public class ClimbStairsSolution {
    public int climbStairsRecursive(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    public int climbStairs2(int n) {
        int[] memo = new int[n + 1];
        return climbStairsMemo(n, memo);
    }

    // 记录中间值
    private int climbStairsMemo(int n, int[] memo) {
        if(memo[n] > 0) {
            return memo[n];
        }
        if(n == 1) {
            memo[1] = 1;
        } else if(n == 2) {
            memo[2] = 2;
        } else {
            memo[n] = climbStairsMemo(n - 1, memo) + climbStairsMemo(n - 2, memo);
        }
        return memo[n];
    }

    // 动态规划
    public int climbStairsDp(int n) {
        if(n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 滚动数组
    public int climbStairs3(int n) {
        if(n == 1) {
            return 1;
        }

        int first = 1;
        int second = 2;

        for(int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    @Test
    public void test() {
//        assertEquals(1, climbStairsRecursive(1));
//        assertEquals(2, climbStairsRecursive(2));
//        assertEquals(3, climbStairsRecursive(3));
//        assertEquals(5, climbStairsRecursive(4));

//        assertEquals(1, climbStairs2(1));
//        assertEquals(2, climbStairs2(2));
//        assertEquals(3, climbStairs2(3));
//        assertEquals(5, climbStairs2(4));

        assertEquals(1, climbStairsDp(1));
        assertEquals(2, climbStairsDp(2));
        assertEquals(3, climbStairsDp(3));
        assertEquals(5, climbStairsDp(4));

        assertEquals(1, climbStairs3(1));
        assertEquals(2, climbStairs3(2));
        assertEquals(3, climbStairs3(3));
        assertEquals(5, climbStairs3(4));

    }
}
