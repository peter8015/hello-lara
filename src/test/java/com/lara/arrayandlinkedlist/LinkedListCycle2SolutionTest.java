package com.lara.arrayandlinkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.ListNode;

public class LinkedListCycle2SolutionTest {

    private final LinkedListCycle2Solution solution = new LinkedListCycle2Solution();

    @Test
    public void testNoCycle() {
        // 构造无环链表: 1 -> 2 -> 3 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        assertNull(solution.linkedListCycle2(head));
    }

    @Test
    public void testHasCycle() {
        // 构造有环链表: 3 -> 2 -> 0 -> -4 -> 2 (cycle starts at node with value 2)
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode nodeNeg4 = new ListNode(-4);

        head.next = node2;
        node2.next = node0;
        node0.next = nodeNeg4;
        nodeNeg4.next = node2; // 创建环

        assertEquals(node2, solution.linkedListCycle2(head));
    }

    @Test
    public void testEmptyList() {
        // 输入为空链表
        assertNull(solution.linkedListCycle2(null));
    }

    @Test
    public void testSingleNodeNoCycle() {
        // 单节点链表，无环
        ListNode head = new ListNode(1);
        assertNull(solution.linkedListCycle2(head));
    }
}
