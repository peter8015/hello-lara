package com.lara.arrayandlinkedlist;

/**
 * @author zhanghaibing
 * @date 2024-02-03
 */
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import utils.ListNode;

public class TwoSumTest {

    @Test
    public void testSum1() {
        // 创建测试用的链表l1和l2
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        // 调用待测函数
        TwoSum twoSum = new TwoSum();
        ListNode result = twoSum.sum1(l1, l2);

        // 验证结果是否正确
        ListNode expected = new ListNode(7);
        expected.next = new ListNode(0);
        expected.next.next = new ListNode(8);

        assertEquals(expected.next.val, result.next.val);
    }

    @Test
    public void testSum1WithNull() {
        // 创建测试用的链表l1和l2，其中l1为null
        ListNode l1 = null;
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        // 调用待测函数
        TwoSum twoSum = new TwoSum();
        ListNode result = twoSum.sum1(l1, l2);

        // 验证结果是否正确
        ListNode expected = new ListNode(5);
        expected.next = new ListNode(6);
        expected.next.next = new ListNode(4);

        assertEquals(expected.next.val, result.next.val);
    }

    // 可以添加更多测试用例来覆盖不同的场景，例如l2为null，或者两个链表都为null等
}
