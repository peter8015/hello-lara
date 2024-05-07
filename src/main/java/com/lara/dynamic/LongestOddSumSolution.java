package com.lara.dynamic;

import java.util.*;


/**
 * 题目描述：给一个值K， 找出一个“最长的” “只有奇数的” “无重复”的数组，数组之和为K（最长的奇数和）
 *
 * input: 16 , 奇数集合：[1, 3, 5, 7, 9, 11, 13, 15]
 * output: [1, 5, 10]
 *
 * 解题思路：回溯算法
 * 1. 初始化一个变量来记录最长的奇数组合长度。
 * 2. 生成所有小于k的奇数集合，并对其进行排序。
 * 3. 使用回溯算法遍历这些奇数，从大到小尝试添加到奇数组合中，同时确保组合中的元素不重复且和为k。
 *
 * 时间复杂度分析：
 * 1. 生成奇数集合的时间复杂度是O(K)，因为需要遍历从1到K的所有奇数。
 * 2. 排序奇数集合的时间复杂度是O(N log N)，其中N为奇数集合的大小。
 * 3. 回溯算法的时间复杂度是O(2^N)，这里N表示奇数集合的大小。
 * 4. 综上所述，整个算法的时间复杂度可以视为O(K + N log N + 2^N)，实际运行时主要取决于奇数集合的大小以及目标和K的具体值。
 *
 * @author zhanghaibing
 * @date 2024-03-19
 */
public class LongestOddSumSolution {
    private static List<Integer> longestOddArray;
    private static int maxLength;

    private static List<Integer> longestOddArray1;
    private static int maxLength1;

    public static List<Integer> findLongestOddSumArray(int K) {
        longestOddArray = new ArrayList<>(); // 记录最长奇数组合
        maxLength = 0;   // 记录当前最长组合的长度

        Set<Integer> oddNumbers = generateOddNumbers(K);
//        Collections.sort(oddNumbers, Collections.reverseOrder());  // 优化

        backtrack(oddNumbers, new ArrayList<>(), K);

        return maxLength > 0 ? longestOddArray : Collections.emptyList();
    }


    /**
     * 回溯算法
     * @param numbers
     * @param currentList
     * @param target
     */
    private static void backtrack(Set<Integer> numbers, List<Integer> currentList, int target) {
        // 基线条件：如果目标和已达到且当前路径不为空，则检查并更新最长数组
        if (target == 0 && !currentList.isEmpty()) {
            if (currentList.size() > maxLength) {
                maxLength = currentList.size();
                longestOddArray.clear();
                longestOddArray.addAll(currentList);
            }
            return;
        }

        // 遍历剩余奇数集合，尝试将每个奇数加入当前路径
        for (Integer number : numbers) {
            if (number <= target && !currentList.contains(number)) {
                currentList.add(number);
                backtrack(numbers, currentList, target - number);
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    /**
     * 生成可能的奇数集合
     *
     * @param K
     * @return
     */
    private static Set<Integer> generateOddNumbers(int K) {
        Set<Integer> oddNumbers = new HashSet<>();
        for (int i = 1; i <= K; i += 2) {
            oddNumbers.add(i);
        }


        return oddNumbers;
    }

    public static void main(String[] args) {
        int K = 16;
        List<Integer> result = findLongestOddSumArray(K);
        System.out.println("Longest odd sum array with sum " + K + ": " + result);
    }
}

