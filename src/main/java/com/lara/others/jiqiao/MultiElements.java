package com.lara.others.jiqiao;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghaibing
 * @date 2024-02-26
 */
public class MultiElements {

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 思路：
     * 1. 哈希表
     * 2. 摩尔投票法
     * - 初始化两个变量：cadidate用于记录众数， count 用于记数
     * - 遍历数组：
     *   - 如果count 为0时，则当前元素为众数，count为1；
     *   - 如果当前元素与cadidate相同，则count加1, 否则减1
     *
     */

    public int maxElement(int[] nums) {
        int r = 0;

        if(nums == null || nums.length == 0) {
            return -1;
        }

        Map<Integer, Integer> maps = new HashMap();

        for(int i = 0; i < nums.length; i++) {
            maps.put(nums[i], maps.getOrDefault(nums[i], 0) + 1);
        }

        int max = 0;
        for(Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if(entry.getValue() >= max) {
                max = entry.getValue();
            }
        }

        for(Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if(entry.getValue() == max) {
                r = entry.getKey();
            }
        }
        return r;
    }

    @Test
    public void test() {
        System.out.println(maxElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
        System.out.println(vote(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

    public int vote(int[] nums) {
        int r = -1;
        int cadidate = -1;
        int count = 0;

        for(int i = 0; i < nums.length; i++) {
            if(count == 0) {
                cadidate = nums[i];
            } else if(cadidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return cadidate;
    }

}
