package com.lara.dynamic;

import com.lara.dynamic.HouseRobberSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseRobberSolutionTest {

    private HouseRobberSolution solution;

    @BeforeEach
    public void setUp() {
        solution = new HouseRobberSolution();
    }

    @Test
    public void testNormalCase() {
        int[] t1 = {2, 7, 9, 3, 1};
        assertEquals(12, solution.rob(t1), "Test 1: Normal case failed");
    }

    @Test
    public void testOnlyOneHouse() {
        int[] t2 = {5};
        assertEquals(5, solution.rob(t2), "Test 2: Only one house failed");
    }

    @Test
    public void testTwoHouses() {
        int[] t3 = {2, 7};
        assertEquals(7, solution.rob(t3), "Test 3: Two houses failed");
    }

    @Test
    public void testAllSameValue() {
        int[] t4 = {5, 5, 5, 5, 5};
        assertEquals(15, solution.rob(t4), "Test 4: All same value failed");
    }

    @Test
    public void testBetterToSkipFirstHouse() {
        int[] t5 = {1, 100, 1};
        assertEquals(100, solution.rob(t5), "Test 5: Better to skip first house failed");
    }

    @Test
    public void testAllZeros() {
        int[] t6 = {0, 0, 0, 0};
        assertEquals(0, solution.rob(t6), "Test 6: All zeros failed");
    }

    @Test
    public void testLargeValues() {
        int[] t7 = {100, 1, 1, 100};
        assertEquals(200, solution.rob(t7), "Test 7: Large values failed");
    }
}