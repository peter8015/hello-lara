package com.lara.arrayandlinkedlist;

import java.util.PriorityQueue;
import org.junit.Test;

/**
 * @author zhanghaibing
 * @date 2024-04-11
 */
public class FindKthLargestX {
    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * <p>
     * 思路：
     * 大顶堆，每次取最大值，然后将最大值替换掉数组中的最小值，
     * 然后将最小值替换掉数组中的最大值，以此类推，直到第k个最大值。
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);

        // 添加所有元素到堆中
        for (int num : nums) {
            maxHeap.add(num);
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.poll();
    }

    @Test
    public void test() {
        // 测试用例1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("The " + k1 + "nd largest element is: " + findKthLargest(nums1, k1));  // 输出应为5

        // 测试用例2
        int[] nums2 = {4, 5, 8, 2, 3, 9, 7};
        int k2 = 3;
        System.out.println("The " + k2 + "rd largest element is: " + findKthLargest(nums2, k2));  // 输出应为7
    }
}
