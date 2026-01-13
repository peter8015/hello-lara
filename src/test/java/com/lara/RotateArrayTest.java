package com.lara;

import com.lara.array.RotateArraySolution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateArrayTest {
    private RotateArraySolution solution;

    @BeforeEach
     void setUp() {
        solution = new RotateArraySolution();
    }

    @Test
    void testStandardRotation() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate(nums, 3);
        int[] expected = {5, 6, 7, 1, 2, 3, 4};
        assertArrayEquals(expected, nums, "Should rotate 3 steps to the right");
    }

    @Test
    void testKGreaterThanLength() {
        int[] nums = {1, 2};
        solution.rotate(nums, 3); // 3 % 2 = 1 rotation
        int[] expected = {2, 1};
        assertArrayEquals(expected, nums, "Should handle k > array length using modulo");
    }

    @Test
    void testZeroRotation() {
        int[] nums = {1, 2, 3};
        solution.rotate(nums, 0);
        int[] expected = {1, 2, 3};
        assertArrayEquals(expected, nums, "Rotating 0 steps should not change the array");
    }

    @Test
    void testSingleElement() {
        int[] nums = {1};
        solution.rotate(nums, 5);
        int[] expected = {1};
        assertArrayEquals(expected, nums, "Single element array should remain unchanged");
    }

    @Test
    void testKIsMultipleOfLength() {
        int[] nums = {1, 2, 3, 4};
        solution.rotate(nums, 4);
        int[] expected = {1, 2, 3, 4};
        assertArrayEquals(expected, nums, "Rotating by the length of the array should result in the same array");
    }
}