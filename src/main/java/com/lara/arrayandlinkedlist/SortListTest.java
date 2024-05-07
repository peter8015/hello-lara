package com.lara.arrayandlinkedlist;


import utils.ListNode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SortListTest {

    private SortList sortList;

    @Before
    public void setUp() {
        sortList = new SortList();
    }

    @Test
    public void testSortList_emptyList() {
        ListNode head = null;
        ListNode sortedHead = sortList.sortList(head);
        Assert.assertNull("The sorted list should be null for an empty list", sortedHead);
    }

    @Test
    public void testSortList_singleElementList() {
        ListNode head = new ListNode(1);
        ListNode sortedHead = sortList.sortList(head);
        Assert.assertSame("The sorted list should be the same as the input for a list with a single element", head, sortedHead);
    }

    @Test
    public void testSortList_listWithNegativeNumbers() {
        ListNode head = new ListNode(-3);
        head.next = new ListNode(0);
        head.next.next = new ListNode(-1);
        ListNode sortedHead = sortList.sortList(head);
        Assert.assertEquals("The sorted list should be -3, -1, 0", -3, sortedHead.val);
        Assert.assertEquals("The second node should be -1", -1, sortedHead.next.val);
        Assert.assertEquals("The third node should be 0", 0, sortedHead.next.next.val);
    }

    @Test
    public void testSortList_listWithDuplicates() {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        ListNode sortedHead = sortList.sortList(head);
        Assert.assertEquals("The sorted list should be 1, 1, 2, 2, 4", 1, sortedHead.val);
        Assert.assertEquals("The second node should be 1", 1, sortedHead.next.val);
        Assert.assertEquals("The third node should be 2", 2, sortedHead.next.next.val);
        Assert.assertEquals("The fourth node should be 2", 2, sortedHead.next.next.next.val);
        Assert.assertEquals("The fifth node should be 4", 4, sortedHead.next.next.next.next.val);
    }

    @Test
    public void testSortList_listWithLargeNumbers() {
        ListNode head = new ListNode(10);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(8);
        ListNode sortedHead = sortList.sortList(head);
        Assert.assertEquals("The sorted list should be 2, 3, 5, 8, 10", 2, sortedHead.val);
        Assert.assertEquals("The second node should be 3", 3, sortedHead.next.val);
        Assert.assertEquals("The third node should be 5", 5, sortedHead.next.next.val);
        Assert.assertEquals("The fourth node should be 8", 8, sortedHead.next.next.next.val);
        Assert.assertEquals("The fifth node should be 10", 10, sortedHead.next.next.next.next.val);
    }
}
