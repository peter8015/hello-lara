package com.lara.arrayandlinkedlist;

import utils.ListNode;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * leetcode142. Give a head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * <p>
 * input: head = [3,2,0,-4], pos = 1
 * output: 2
 * <p>
 * os:
 * - "I’m going to use a Sanity Check here: If the head is null or there's only one node, a cycle is impossible, so I'll return null."
 *
 * @author zhanghaibing
 * @date 2026-02-09
 */
public class LinkedListCycle2Solution {

    /**
     * Detects the starting node of a cycle in a linked list using Floyd's Cycle Detection Algorithm.
     * <p>
     * Approach:
     * 1. Use two pointers (slow and fast) to detect if a cycle exists.
     * 2. When a cycle is detected (slow == fast), reset one pointer to the head.
     * 3. Move both pointers one step at a time until they meet again; this meeting point is the start of the cycle.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head The head node of the linked list.
     * @return The node where the cycle begins, or null if there is no cycle.
     */
    public ListNode linkedListCycle2x(ListNode head) {
        // handle edge case: if head is null or head.next is null, there cannot be a cycle
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected
            if (slow == fast) {
                // Phase 2: Find the start of the cycle
                ListNode p1 = head;
                ListNode p2 = slow;

                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1; // Start of the cycle
            }
        }
        return null; // No cycle found
    }


    public ListNode linkedListCycle2(ListNode head) {
        // handle edge cases
        if(head == null || head.next == null) return null;

        // use two pointers
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // find the cycle
            if(fast == slow) {
                ListNode p1 = head;
                ListNode p2 = slow;

                while(p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;

                }
                return p1;
            }
        }
        return null;
    }
}