package com.lara.arrayandlinkedlist;


/**
 * @author zhanghaibing
 * @date 2024-02-23
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ThreeSumTest {

    ThreeSum threeSum = new ThreeSum();
    @Test
    public void testThreeSum() {
        // 测试用例1：存在和为0的三元组
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1)
        );
        assertEquals(expected1, threeSum.threeSum(nums1));

//        // 测试用例2：不存在和为0的三元组
//        int[] nums2 = {1, 2, 3, 4};
//        List<List<Integer>> expected2 = new ArrayList<>();
//        assertEquals(expected2, threeSum.threeSum(nums2));
//
//        // 测试用例3：只有一个解
//        int[] nums3 = {-4, -2, -2, 0, 1, 2, 2};
//        List<List<Integer>> expected3 = Arrays.asList(Arrays.asList(-4, -2, 2));
//        assertEquals(expected3, threeSum.threeSum(nums3));
//
//        // 测试用例4：数组中有重复元素，但解不重复
//        int[] nums4 = {-1, 0, 0, 0, 1, 2};
//        List<List<Integer>> expected4 = Arrays.asList(
//                Arrays.asList(-1, 0, 1),
//                Arrays.asList(0, 0, 0)
//        );
//        assertEquals(expected4, threeSum.threeSum(nums4));
    }


    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, -2, -1};

        int[] expected = new int[]{};
    }
}


