package com.lara.arrayandlinkedlist;

/**
 *
 *
 * @author zhanghaibing
 * @date 2024-11-03
 */

import org.checkerframework.checker.units.qual.A;

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


    public int[][] merge1(int[][] intervals) {
        // sort all the arrays
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        // loop the intervals, compare result[0] with current[1]
        for (int i = 0; i < intervals.length; i++) {
            int[] last = res.get(res.size() - 1);
            int[] current = intervals[i];

            if (current[0] <= last[1]) {
                // over lamp, meger
                last[1] = Math.max(last[1], current[1]);
            } else {
                res.add(current);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /**
     * {{1, 3},{2, 6},  {8, 10} {15, 18},};
     * <p>
     * - arrays.sort to sort the intervals
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] intervals = {{2, 6}, {1, 3}, {15, 18}, {8, 10}};

        MergeIntervals solution = new MergeIntervals();
        int[][] mergedIntervals = solution.merge1(intervals);
        for (int[] interval : mergedIntervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}

/**
 * Step 1 — Clarify
 * We are given a list of intervals, we need to merge any overlapping or touching ranges and return a list of non-overlapping intervals covering all input ranges.
 * Step 2 — Approach
 * First we sort intervals by their start value, not end. We maintain a result list storing merged ranges. We compare each interval with the last merged one: if overlapping, merge them; otherwise add the new interval separately.
 * Step 3 — Walk the code
 * This code has two bugs: it sorts by end instead of start, and loops from index 0 which rechecks the first interval we already added. After fixing the sort comparator and starting loop at i=1: we take the last merged interval, check if current start ≤ last end, update the end to the larger value if overlapping, otherwise add current interval to result. Finally convert list back to 2D array.
 * Step 4 — Complexity
 * Time complexity is dominated by sorting: O(n log n). Space complexity is O(n) for the result list.
 *
 * Full continuous speaking version (read aloud, ~30s)
 * Step one, clarify the problem: given multiple intervals, merge all overlapping ones and return cleaned non-overlapping ranges.
 * Step two, my approach: sort intervals by start time, use a list to keep merged results, compare each interval with the latest merged range to decide merge or append.
 * Step three, walk through the code: this implementation has two flaws — sorted by end incorrectly and loops from index zero. After fixing those, we check overlap, extend the last interval’s end if overlapped, or add a new interval. At last convert list to 2D array.
 * Step four, complexity analysis: time O(n log n) from sorting, space O(n) for output storage.
 */