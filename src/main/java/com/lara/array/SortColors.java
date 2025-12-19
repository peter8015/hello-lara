package com.lara.array;

/**
 * @author zhanghaibing
 * @date 2025-12-18
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null) return;

        // detch native flag algorithm
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                extracted(nums, low, mid);

                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                extracted(nums, mid, high);

                high--;
            }
        }
    }

    private static void extracted(int[] nums, int low, int mid) {
        int temp = nums[low];
        nums[low] = nums[mid];
        nums[mid] = temp;
    }
}
