package com.lara.arrayandlinkedlist;

import utils.ListNode;

import java.util.HashSet;

/**
 * leetcode160 Given the heads of two singly linked-list, return the node at which two list intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 *
 * @author zhanghaibing
 * @date 2026-01-13
 */
public class IntersectTwoLinkedListSolution {
    /**
     * os:
     * 1. clarify and confirm: functional, constraints & scale, edge cases
     * 2.
     * - if the two lists have no intersection at all, return null?
     * - if any of the two lists is empty, return null?
     * - A navie approach would be to iterate through both lists and compare each node. That would be O(n^2) time.
     * - We can use two pointers to iterate through both lists simultaneously.
     */
    public ListNode intersectTwoLinkedListy(ListNode headA, ListNode headB) {
        // boundary check
        if(headA == null || headB == null) return null;

        // Time Complexity: O(m+n), where m and n are the lengths of the two linked lists respectively
        // Space Complexity: O(m), where m is the length of list A
        HashSet<ListNode> data = new HashSet<>();

        while(headA != null) {
            data.add(headA);
            headA = headA.next;
        }

        while(headB != null) {
            if(data.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    // Time Complexity: O(m+n), where m and n are the lengths of the two linked lists respectively
    // Space Complexity: O(1)
    public ListNode intersectTwoLinkedListx(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;

        // Continue looping while a and b are not equal
        // If both pointers become null, it means there's no intersection, and the loop will naturally end
        while(a != b) {
            // When a reaches the end of its list, redirect it to headB
            a = a == null ? headB : a.next;
            // When b reaches the end of its list, redirect it to headA
            b = b == null ? headA : b.next;
        }
        // If there is an intersection, both a and b will point to the intersection node
        // If there is no intersection, both a and b will eventually be null
        return a;
    }

    public ListNode intersectTwoLinkedList(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        // two pointer to decrease iterat
        ListNode a = headA, b = headB;

        while(a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

}
