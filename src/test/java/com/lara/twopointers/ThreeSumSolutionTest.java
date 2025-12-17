package com.lara.twopointers;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author zhanghaibing
 * @date 2024-02-23
 */
public class ThreeSumSolutionTest {
    @Test
    public void testDuplicates() {
        ThreeSumSolution solver = new ThreeSumSolution();
        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1)
        );

        List<List<Integer>> actual = solver.threeSum5(nums);

        // Convert it into set for comparison to avoid issues related to order
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }
    ThreeSumSolution threeSum = new ThreeSumSolution();
}