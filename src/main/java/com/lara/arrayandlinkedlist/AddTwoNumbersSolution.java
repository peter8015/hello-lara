package com.lara.arrayandlinkedlist;

import utils.ListNode;

/**
 *
 * leetcode2 Add Two Numbers
 * Give two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * output: 7 -> 0 -> 8
 *
 * @author zhanghaibing
 * @date 2026-02-10
 */
public class AddTwoNumbersSolution {
    /**
     * time complexity: O(max(m, n))
     * space complexity: O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersx(ListNode l1, ListNode l2) {
        // handle edge cases
        if (l1 == null && l2 == null) {
            throw new RuntimeException("Both l1 and l2 cannot be null.");
        }

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        int v1 = 0, v2 = 0, res = 0, carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            v1 = l1 == null ? 0 : l1.val;
            v2 = l2 == null ? 0 : l2.val;

            res = v1 + v2 + carry;
            carry = res / 10;
            temp.next = new ListNode(res % 10);

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            temp = temp.next;
        }
        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // handle edge cases
        if(l1 == null || l2 == null) return l1 == null ? l2 : l1;

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        int v1 = 0, v2 = 0, sum = 0, carry = 0;

        return null;
    }
}
