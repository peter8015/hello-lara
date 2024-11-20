package com.lara.arrayandlinkedlist;

/**
 * @author zhanghaibing
 * @date 2024-11-03
 */
public class TestMergeIntervals {
    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();

        // 测试用例 1
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = mergeIntervals.merge(intervals1);
        printResult(result1);  // 应输出 [[1, 6], [8, 10], [15, 18]]



        // 测试用例 2
        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] result2 = mergeIntervals.merge(intervals2);
        printResult(result2);  // 应输出 [[1, 5]]

        // 测试用例 3
        int[][] intervals3 = {{1, 2}, {3, 4}, {5, 6}};
        int[][] result3 = mergeIntervals.merge(intervals3);
        printResult(result3);  // 应输出 [[1, 2], [3, 4], [5, 6]]

        // 测试用例 4
        int[][] intervals4 = {};
        int[][] result4 = mergeIntervals.merge(intervals4);
        printResult(result4);  // 应输出 []

        // 测试用例 5
        int[][] intervals5 = {{1, 10}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result5 = mergeIntervals.merge(intervals5);
        printResult(result5);  // 应输出 [[1, 10], [15, 18]]
    }

    private static void printResult(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + ", " + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
