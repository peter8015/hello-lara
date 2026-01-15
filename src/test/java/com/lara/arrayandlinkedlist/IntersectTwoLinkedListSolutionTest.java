package com.lara.arrayandlinkedlist;

import org.junit.jupiter.api.Test;
import utils.ListNode;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectTwoLinkedListSolutionTest {

    private IntersectTwoLinkedListSolution solution = new IntersectTwoLinkedListSolution();

    @Test
    public void testIntersectAtMiddle() {
        // 创建链表 A: 4 -> 1 -> 8 -> 4 -> 5
        ListNode common = createList(new int[]{8, 4, 5});
        ListNode headA = createList(new int[]{4, 1});
        headA.next.next = common;

        // 创建链表 B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        ListNode headB = createList(new int[]{5, 6, 1});
        headB.next.next.next = common;

        ListNode result = solution.intersectTwoLinkedList(headA, headB);
        assertNotNull(result);
        assertEquals(8, result.val);
    }

    @Test
    public void testNoIntersection() {
        ListNode headA = createList(new int[]{1, 2, 3});
        ListNode headB = createList(new int[]{4, 5, 6});

        ListNode result = solution.intersectTwoLinkedList(headA, headB);
        assertNull(result);
    }

    @Test
    public void testOneEmptyList() {
        ListNode headA = createList(new int[]{1, 2, 3});
        ListNode headB = null;

        ListNode result = solution.intersectTwoLinkedList(headA, headB);
        assertNull(result);
    }

    @Test
    public void testBothEmptyLists() {
        ListNode result = solution.intersectTwoLinkedList(null, null);
        assertNull(result);
    }

    @Test
    public void testSameList() {
        ListNode head = createList(new int[]{1, 2, 3});
        
        ListNode result = solution.intersectTwoLinkedList(head, head);
        assertSame(head, result);
    }

    @Test
    public void testIntersectAtHead() {
        ListNode head = createList(new int[]{1, 9, 7});
        ListNode headA = head;
        ListNode headB = createList(new int[]{3});
        headB.next = head;

        ListNode result = solution.intersectTwoLinkedList(headA, headB);
        assertNotNull(result);
        assertEquals(1, result.val);
    }

    @Test
    public void testSingleNodeIntersection() {
        ListNode intersectNode = new ListNode(8);
        ListNode headA = new ListNode(4);
        headA.next = intersectNode;
        
        ListNode headB = new ListNode(5);
        headB.next = intersectNode;

        ListNode result = solution.intersectTwoLinkedList(headA, headB);
        assertNotNull(result);
        assertEquals(8, result.val);
    }

    /**
     * Helper method to create a linked list from an array
     */
    private ListNode createList(int[] values) {
        if (values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
}
