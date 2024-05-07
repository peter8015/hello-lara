package com.lara.arrayandlinkedlist;

/**
 * @author zhanghaibing
 * @date 2024-03-08
 */
import org.junit.Test;
import utils.ListNode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {

    private Palindrome util = new Palindrome();

    @Test
    public void testIsPalindrome() {
        // 测试用例1：空链表
        ListNode head1 = null;
        assertTrue(util.isPalindrome(head1));

        // 测试用例2：单个元素链表
        ListNode head2 = new ListNode(1);
        assertTrue(util.isPalindrome(head2));

        // 测试用例3：两个元素不同，不是回文链表
        ListNode node3_1 = new ListNode(1);
        ListNode node3_2 = new ListNode(2);
        node3_1.next = node3_2;
        assertFalse(util.isPalindrome(node3_1));

        // 测试用例4：两个元素相同，是回文链表
        ListNode node4_1 = new ListNode(1);
        ListNode node4_2 = new ListNode(1);
        node4_1.next = node4_2;
        assertTrue(util.isPalindrome(node4_1));

        // 测试用例6：完整回文链表
        ListNode head6_1 = new ListNode(1);
        ListNode node6_2 = new ListNode(2);
        ListNode node6_3 = new ListNode(2);
        ListNode node6_4 = new ListNode(1);
        head6_1.next = node6_2;
        node6_2.next = node6_3;
        node6_3.next = node6_4;
        assertTrue(util.isPalindrome(head6_1));
    }
}
