package com.lara.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode56.Merge Intervals — Medium
 *
 * @author zhanghaibing
 * @date 2025-12-22
 */
public class MergeIntervalsSolution {

    // sorting + greedy
    //time O(nlogn)
    public int[][] mergeIntervals(int[][] nums) {
        // Edge case handling
        if(nums.length == 0) return nums;

        // sorting O(nlogn)
        Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
        System.out.println("Sorted intervals: " + Arrays.deepToString(nums));

        int[] currentInterval = nums[0];
        List<int[]> result = new ArrayList<>();

        // greedy liner scan O(n)
        for(int i = 0; i < nums.length; i++) {
            int currentEnd = currentInterval[1];
            int nextStart = nums[i][0];
            int nextEnd = nums[i][1];

            if(currentEnd >= nextStart) {
                // Overlap detected: Extend the end point greedily
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                result.add(currentInterval);
                currentInterval = nums[i];
            }
        }
        result.add(currentInterval);

        System.out.println(Arrays.deepToString(result.toArray()));
        return result.toArray(new int[result.size()][]);
    }



    public int[][] mergeIntervals2(int[][] intervals) {

        int[][] result = new int[][]{};
        if(intervals == null || intervals.length == 0) return result;


        for(int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];




        }


        return result;

    }

}
