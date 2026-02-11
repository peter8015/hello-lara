package com.lara.arrayandlinkedlist;

import org.junit.jupiter.api.Test;
import utils.ListNode;

import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersSolutionTest {

    private final AddTwoNumbersSolution solution = new AddTwoNumbersSolution();

    // 辅助方法：创建链表
    private ListNode createLinkedList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    // 辅助方法：比较两个链表是否相等
    private boolean areListsEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }

    @Test
    void testNormalCase() {
        // 输入: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        // 输出: 7 -> 0 -> 8
        ListNode l1 = createLinkedList(new int[]{2, 4, 3});
        ListNode l2 = createLinkedList(new int[]{5, 6, 4});
        ListNode expected = createLinkedList(new int[]{7, 0, 8});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertTrue(areListsEqual(expected, result));
    }

    @Test
    void testWithCarry() {
        // 输入: (9 -> 9) + (1)
        // 输出: 0 -> 0 -> 1
        ListNode l1 = createLinkedList(new int[]{9, 9});
        ListNode l2 = createLinkedList(new int[]{1});
        ListNode expected = createLinkedList(new int[]{0, 0, 1});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertTrue(areListsEqual(expected, result));
    }

    @Test
    void testDifferentLengths() {
        // 输入: (1 -> 8) + (0)
        // 输出: 1 -> 8
        ListNode l1 = createLinkedList(new int[]{1, 8});
        ListNode l2 = createLinkedList(new int[]{0});
        ListNode expected = createLinkedList(new int[]{1, 8});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertTrue(areListsEqual(expected, result));
    }

    @Test
    void testSingleElement() {
        // 输入: (5) + (5)
        // 输出: 0 -> 1
        ListNode l1 = createLinkedList(new int[]{5});
        ListNode l2 = createLinkedList(new int[]{5});
        ListNode expected = createLinkedList(new int[]{0, 1});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertTrue(areListsEqual(expected, result));
    }

    @Test
    void testOneEmptyList() {
        // 输入: () + (1 -> 2 -> 3)
        // 输出: 1 -> 2 -> 3
        ListNode l1 = null;
        ListNode l2 = createLinkedList(new int[]{1, 2, 3});
        ListNode expected = createLinkedList(new int[]{1, 2, 3});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertTrue(areListsEqual(expected, result));
    }
}
