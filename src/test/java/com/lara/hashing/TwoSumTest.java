package com.lara.hashing;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TwoSumTest {

    private TwoSumSolution twoSum1;

    @BeforeEach
    public void setUp() {
        twoSum1 = new TwoSumSolution();
    }

    @Test
    public void twoSum_NullArray_ReturnsEmptyArray() {
        int[] result = twoSum1.twoSum(null, 0);
        assertEquals(0, result.length);
    }

    @Test
    public void twoSum_ArrayWithLessThanTwoElements_ReturnsEmptyArray() {
        int[] result = twoSum1.twoSum(new int[]{1}, 0);
        assertEquals(0, result.length);
    }

    @Test
    public void twoSum_ValidInput_ReturnsCorrectIndices() {
        int[] result = twoSum1.twoSum(new int[]{3, 2, 4}, 6);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    public void twoSum_NoValidPair_ReturnsEmptyArray() {
        int[] result = twoSum1.twoSum(new int[]{1, 2, 3}, 7);
        assertEquals(0, result.length);
    }

    @Test
    public void twoSum_DuplicateValues_ReturnsFirstPair() {
        int[] result = twoSum1.twoSum(new int[]{3, 3, 3}, 6);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    public void twoSum_MultiplePairs_ReturnsFirstPair() {
        int[] result = twoSum1.twoSum4(new int[]{1, 2, 3, 4, 5}, 9);
        assertArrayEquals(new int[]{3, 4}, result);
    }
}
