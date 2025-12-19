package com.lara;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.lara.array.SortColors;
import org.junit.jupiter.api.Test;

public class SortColorsTest {

    private final SortColors solution = new SortColors();

    @Test
    public void testStandardCase() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        int[] expected = {0, 0, 1, 1, 2, 2};
        solution.sortColors(nums);
        assertArrayEquals(expected, nums, "标准无序数组排序失败");
    }

    @Test
    public void testTwoColors() {
        int[] nums = {2, 0, 1};
        int[] expected = {0, 1, 2};
        solution.sortColors(nums);
        assertArrayEquals(expected, nums, "三个不同颜色排序失败");
    }

    @Test
    public void testSingleColor() {
        int[] nums = {0, 0, 0};
        int[] expected = {0, 0, 0};
        solution.sortColors(nums);
        assertArrayEquals(expected, nums, "全同颜色数组排序失败");
    }

    @Test
    public void testAlreadySorted() {
        int[] nums = {0, 1, 2};
        int[] expected = {0, 1, 2};
        solution.sortColors(nums);
        assertArrayEquals(expected, nums, "已排序数组排序失败");
    }

    @Test
    public void testEmptyAndSingleElement() {
        int[] nums1 = {};
        solution.sortColors(nums1);
        assertArrayEquals(new int[]{}, nums1);

        int[] nums2 = {1};
        solution.sortColors(nums2);
        assertArrayEquals(new int[]{1}, nums2);
    }
}
