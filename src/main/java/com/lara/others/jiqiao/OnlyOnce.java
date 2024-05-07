package com.lara.others.jiqiao;


import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * @author zhanghaibing
 * @date 2024-02-26
 */
public class OnlyOnce {
    /**
     * 136. 只出现一次的数字
     * 题目：给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 时间复杂度O(n)
     * 思路：
     * 1. 哈希表
     * 2. 异或运算：a ^ a = 1
     *
     */
    public int showOnce(int[] nums) {
        Map<Integer, Integer> maps = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            maps.put(nums[i], maps.getOrDefault(nums[i], 0) + 1);
        }

        int val = 0;
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            val = entry.getValue();
            if (val == 1) {
                return entry.getKey();
            }
        }
        return val;
    }

    public int showOnce2(int[] nums) {
        int r = 0;

        for(int i = 0; i < nums.length; i++) {
            r = r ^ nums[i];
        }

        return r;
    }


    @Test
    public void test() {
        int k = showOnce2(new int[]{1, 2, 3, 3, 2, 1, 4});
        assertEquals(4, k);
    }

    @Test
    public void test2() {
        int a = 2, b = 3;
        int r = a ^ a ^ b;
        System.out.println(r);
    }

}