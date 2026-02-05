package com.lara.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerWithMostWaterSolutionTest {

    private final ContainerWithMostWaterSolution solution = new ContainerWithMostWaterSolution();

    @Test
    public void testNormalCase() {
        // Given
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        // When
        int result = solution.containerWithMostWater(height);

        // Then
        assertEquals(49, result, "The maximum area should be 49 for the given input.");
    }

    @Test
    public void testEmptyArray() {
        // Given
        int[] height = {};

        // When
        int result = solution.containerWithMostWater(height);

        // Then
        assertEquals(0, result, "The result should be 0 for an empty array.");
    }

    @Test
    public void testSingleElement() {
        // Given
        int[] height = {5};

        // When
        int result = solution.containerWithMostWater(height);

        // Then
        assertEquals(0, result, "The result should be 0 for a single element array.");
    }

    @Test
    public void testTwoElements() {
        // Given
        int[] height = {1, 1};

        // When
        int result = solution.containerWithMostWater(height);

        // Then
        assertEquals(1, result, "The result should be 1 for two elements with same height.");
    }

    @Test
    public void testAllElementsSame() {
        // Given
        int[] height = {3, 3, 3, 3};

        // When
        int result = solution.containerWithMostWater(height);

        // Then
        assertEquals(9, result, "The result should be 9 for all elements being the same.");
    }

    @Test
    public void testLargeInput() {
        // Given
        int[] height = new int[10000];
        for (int i = 0; i < height.length; i++) {
            height[i] = i % 100; // Simulate varying heights
        }

        // When
        int result = solution.containerWithMostWater(height);

        // Then
        assertTrue(result > 0, "The result should be greater than 0 for large inputs.");
    }
}
