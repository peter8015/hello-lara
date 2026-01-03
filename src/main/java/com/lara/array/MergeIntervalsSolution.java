package com.lara.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode56.Merge Intervals — Medium
 *
 * @author zhanghaibing
 * @date 2025-12-22
 */
public class MergeIntervalsSolution {

    // sorting + greedy  O(nlogn)
    public int[][] mergeIntervals(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return intervals;

        // sort by the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // scan and merge
        int[] current = intervals[0];
        List<int[]> result = new ArrayList<>();
        for(int i = 1; i < intervals.length; i++) {
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if(current[1] >= nextStart) {
                current[1] = Math.max(current[1], nextEnd);
            } else {
                result.add(current);
                current = intervals[i];
            }
        }
        result.add(current);

        return result.toArray(new int[result.size()][]);
    }

}
