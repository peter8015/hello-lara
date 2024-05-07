package com.lara.arrayandlinkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaibing
 * @date 2024-03-08
 */
public class PalindromeNumber {

    /**
     * 思路一：进阶，反转一半的数字
     * 1. 判断特殊情况：数字为负数，返回false。
     * 2. 分情况判断：奇数，偶数。
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * 思路二：转化为数组进行判断
     * 1. 将数字转化为数组。
     * 2. 双指针进行判断。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */

    public boolean isPalindromeNumber(int x) {
        // 判断特殊情况
        if(x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }

        // 比较反转一半的数字
        int revertNumber = 0;
        while(x > revertNumber) {
            revertNumber = revertNumber * 10 + x % 10;
            x /= 10;
        }

        // 12321, x = 12, revertNumber = 123,
        return x == revertNumber || x == revertNumber % 10;
    }

    public boolean isPalindromeNubmer(int x) {
        String s = x + "";

        // 转化为数组
        List<Character> arr = new ArrayList();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr.add(c);
        }

        // 利用双指针判断
        int start = 0, end = arr.size() - 1;
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(start) != arr.get(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
