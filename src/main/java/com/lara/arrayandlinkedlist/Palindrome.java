package com.lara.arrayandlinkedlist;


import org.junit.Test;
import utils.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaibing
 * @date 2024-03-08
 */
public class Palindrome {

    /**
     * 234. 回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为
     * 回文链表, 如果是，返回 true ；否则，返回 false 。
     *
     * 思路：转化为数组，用双指针来判断
     * 1. 链表值加入数组：遍历链表，把值加入数组。
     * 2. 双指针遍历数组：从头和尾同时遍历，如果相等，继续遍历。
     *
     * 时间复杂度：
     * 1. 步骤1时间复杂度为O(n)
     * 2. 步骤2时间复杂度为O(n/2)
     * 3. 整体的时间复杂度为O(2n),即为O(n)
     */
    public boolean isPalindrome(ListNode head) {
        // 1. 链表值加入到数组
        List<Integer> arr = new ArrayList();

        while(head != null) {
            arr.add(head.val);
            head = head.next;
        }

        // 2. 双指针遍历数组
        int start = 0, end = arr.size() - 1;

        while(start < end) {
            if(arr.get(start) != arr.get(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }


    @Test
    public void test() {
        int k = 121;
        String s = k + "";

        System.out.println(s.charAt(0));
    }
}
