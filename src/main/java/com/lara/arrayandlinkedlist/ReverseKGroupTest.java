package com.lara.arrayandlinkedlist;


import org.junit.Test;
import utils.ListNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ReverseKGroupTest {

    @Test
    public void testReverseKGroup() {
        // 测试用例1：k=3，链表为空
        ListNode head1 = null;
        assertEquals(head1, new ReverseKGroup().reverseKGroup(head1, 3));

        // 测试用例2：k=3，链表长度小于k
        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        node21.next = node22;
        ListNode head2 = node21;
        assertEquals(node21, new ReverseKGroup().reverseKGroup(head2, 3));
    }

    @Test
    public void testReverseKGroupWithFiveNodes() {
        // 测试用例：k=2，链表包含五个节点
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = node1;

        ListNode expected = new ListNode(2);
        expected.next = new ListNode(1);
        expected.next.next = new ListNode(4);
        expected.next.next.next = new ListNode(3);
        expected.next.next.next.next = new ListNode(5);

//        assertEquals(expected, new ReverseKGroup().reverseKGroup(head, 2));
        assertTrue(compareLinkedList(expected, new ReverseKGroup().reverseKGroup(head, 2)));

    }

    // 深度比较两个链表是否相等的方法
    private boolean compareLinkedList(ListNode expected, ListNode actual) {
        while (expected != null && actual != null) {
            if (expected.val != actual.val) {
                return false;
            }
            expected = expected.next;
            actual = actual.next;
        }
        return expected == null && actual == null;
    }
}
