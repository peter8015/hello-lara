package com.lara.arrayandlinkedlist;

/**
 *
 *
 * @author zhanghaibing
 * @date 2024-11-03
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }

        // Step 1: Sort the intervals by their start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]); // Initialize the merged list with the first interval

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] lastMerged = merged.get(merged.size() - 1);

            // Step 2: Check for overlap
            if (current[0] <= lastMerged[1]) {
                // Merge the intervals
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // No overlap, add the current interval
                merged.add(current);
            }
        }

        // Convert the list to an array and return
        return merged.toArray(new int[merged.size()][]);
    }
}