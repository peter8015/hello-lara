package com.lara.dynamic;

/**
 * leetcode 70. 爬楼梯 简单
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * 解题思路：由于子问题存在重叠（即到达某一层的方法依赖于到达前一层或前二层的方法），可以使用动态规划法避免重复计算。
 * 定义状态dp[n]表示到达n阶楼梯的方法数。
 * - 基础情况：
 * - 当n = 0时，已经在楼顶，所以dp[0]=1种方法。
 * - 当n = 1时，需要走1步，所以有dp[1]=1种方法。
 * - 状态转移方程：
 * - 当n > 1时，到达第n阶的方法数等于到达第n-1阶和n-2阶的方法数之和，即dp[n]=dp[n -1] + dp[n - 2].
 * <p>
 * 解法一：递归，时间复杂度为O(n^2)
 * 解法二：记录递归，时间复杂度为O(n)
 * 解法三：动态规划，时间复杂度为O(n)
 *
 * @author zhanghaibing
 * @date 2024-03-19
 * <p>
 * Description:
 * You are climbing a staircase. It takes you either one or two steps to reach the top. Each time you can climb one or two steps.
 * How many ways to reach the top.
 * <p>
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * You are climbing a staircase. It takes you n steps to reach the top.  ]
 * <p>
 * 你正在爬楼梯。需要爬 n 级台阶才能到达顶部。 每次你可以爬 1 级或 2 级台阶。有多少种不同的方式可以爬到顶部？
 * You are climbing a staircase. It takes you n steps to reach the top. Each time you can either climb 1 or 2 steps.
 * How many distinct ways can you climb to the top?
 */
public class ClimbStairsSolution {
    public int climbStairsRecursive(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    // 记录中间值
    private int climbStairsMemo(int n, int[] memo) {
        if (memo[n] > 0) {
            return memo[n];
        }
        if (n == 1) {
            memo[1] = 1;
        } else if (n == 2) {
            memo[2] = 2;
        } else {
            memo[n] = climbStairsMemo(n - 1, memo) + climbStairsMemo(n - 2, memo);
        }
        return memo[n];
    }

    // 动态规划
    public int climbStairsDp(int n) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 滚动数组
    public int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
