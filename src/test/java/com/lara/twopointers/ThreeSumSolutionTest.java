package com.lara.twopointers;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author zhanghaibing
 * @date 2024-02-23
 */

public class ThreeSumSolutionTest {

    private final ThreeSumSolution solution = new ThreeSumSolution();

    @org.junit.jupiter.api.Test
    public void testNormalCase() {
        // Given
        int[] nums = {-1, 0, 1, 2, -1, -4};

        // When
        List<List<Integer>> result = solution.threeSum(nums);

        // Then
        Assertions.assertEquals(2, result.size(), "Should return two triplets.");
        assertTrue(result.contains(List.of(-1, -1, 2)), "Should contain triplet [-1, -1, 2].");
        assertTrue(result.contains(List.of(-1, 0, 1)), "Should contain triplet [-1, 0, 1].");
    }

    @org.junit.jupiter.api.Test
    public void testNoTriplets() {
        // Given
        int[] nums = {1, 2, 3};

        // When
        List<List<Integer>> result = solution.threeSum(nums);

        // Then
        assertTrue(result.isEmpty(), "Should return empty list when no triplets exist.");
    }

    @org.junit.jupiter.api.Test
    public void testAllZeros() {
        // Given
        int[] nums = {0, 0, 0};

        // When
        List<List<Integer>> result = solution.threeSum(nums);

        // Then
        Assertions.assertEquals(1, result.size(), "Should return one triplet.");
        assertTrue(result.contains(List.of(0, 0, 0)), "Should contain triplet [0, 0, 0].");
    }

    @org.junit.jupiter.api.Test
    public void testDuplicateElements() {
        // Given
        int[] nums = {-2, 0, 0, 2, 2};

        // When
        List<List<Integer>> result = solution.threeSum(nums);

        // Then
        Assertions.assertEquals(1, result.size(), "Should return one unique triplet.");
        assertTrue(result.contains(List.of(-2, 0, 2)), "Should contain triplet [-2, 0, 2].");
    }

    @org.junit.jupiter.api.Test
    public void testEdgeCaseEmptyArray() {
        // Given
        int[] nums = {};

        // When
        List<List<Integer>> result = solution.threeSum(nums);

        // Then
        assertTrue(result.isEmpty(), "Should return empty list for empty input.");
    }

    @org.junit.jupiter.api.Test
    public void testEdgeCaseLessThanThreeElements() {
        // Given
        int[] nums = {1, 2};

        // When
        List<List<Integer>> result = solution.threeSum(nums);

        // Then
        assertTrue(result.isEmpty(), "Should return empty list for arrays with less than 3 elements.");
    }
}