package com.lara.twopointers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrappingRainWaterTest {

    private TrappingRainWater trappingRainWater;

    @BeforeEach
    public void setUp() {
        trappingRainWater = new TrappingRainWater();
    }

    @Test
    public void trapWater_NullArray_ReturnsZero() {
        int[] height = null;
        int result = trappingRainWater.trapWater(height);
        assertEquals(0, result);
    }

    @Test
    public void trapWater_EmptyArray_ReturnsZero() {
        int[] height = new int[]{};
        int result = trappingRainWater.trapWater(height);
        assertEquals(0, result);
    }

    @Test
    public void trapWater_SingleElement_ReturnsZero() {
        int[] height = new int[]{1};
        int result = trappingRainWater.trapWater(height);
        assertEquals(0, result);
    }

    @Test
    public void trapWater_AllSameElements_ReturnsZero() {
        int[] height = new int[]{2, 2, 2, 2};
        int result = trappingRainWater.trapWater(height);
        assertEquals(0, result);
    }

    @Test
    public void trapWater_StrictlyIncreasing_ReturnsZero() {
        int[] height = new int[]{1, 2, 3, 4, 5};
        int result = trappingRainWater.trapWater(height);
        assertEquals(0, result);
    }

    @Test
    public void trapWater_StrictlyDecreasing_ReturnsZero() {
        int[] height = new int[]{5, 4, 3, 2, 1};
        int result = trappingRainWater.trapWater(height);
        assertEquals(0, result);
    }

    @Test
    public void trapWater_MultiplePeaksAndValleys_CorrectWaterAmount() {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = trappingRainWater.trapWater(height);
        assertEquals(6, result);
    }

    @Test
    public void trapWater_ComplexArray_CorrectWaterAmount() {
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        int result = trappingRainWater.trapWater(height);
        assertEquals(9, result);
    }
}
