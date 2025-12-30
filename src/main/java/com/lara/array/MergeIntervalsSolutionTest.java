package com.lara.array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class MergeIntervalsSolutionTest {

    private MergeIntervalsSolution solution;

    @BeforeEach
    void setUp() {
        solution = new MergeIntervalsSolution();
    }

    @Test
    void testMergeIntervalsWithOverlappingIntervals() {
        int[][] input = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        // Note: This test will fail until the implementation is complete
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }

    @Test
    void testMergeIntervalsWithUnsortedIntervals() {
        int[][] input = {{2, 6}, {1, 3}, {8, 10}, {15, 18}}; // 未排序的输入
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }

    @Test
    void testMergeIntervalsWithNoOverlappingIntervals() {
        int[][] input = {{1, 4}, {5, 8}, {9, 12}};
        int[][] expected = {{1, 4}, {5, 8}, {9, 12}};
        // Note: This test will fail until the implementation is complete
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }

    @Test
    void testMergeIntervalsWithAllOverlappingIntervals() {
        int[][] input = {{1, 4}, {4, 5}, {5, 6}};
        int[][] expected = {{1, 6}};
        // Note: This test will fail until the implementation is complete
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }

    @Test
    void testMergeIntervalsWithEmptyArray() {
        int[][] input = {};
        int[][] expected = {};
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }

    @Test
    void testMergeIntervalsWithSingleInterval() {
        int[][] input = {{1, 5}};
        int[][] expected = {{1, 5}};
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }

    @Test
    void testMergeIntervalsWithTwoMergableIntervals() {
        int[][] input = {{1, 4}, {2, 3}};
        int[][] expected = {{1, 4}};
        // Note: This test will fail until the implementation is complete
        assertArrayEquals(expected, solution.mergeIntervals(input));
    }
}
