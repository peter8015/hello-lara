package com.lara.arrayandlinkedlist;

import utils.ListNode;

import static junit.framework.TestCase.assertEquals;

/**
 * @author zhanghaibing
 * @date 2024-02-03
 */
public class TwoLinkNodeSumSolution {


    // 方法一：迭代法

    // 方法二：递归法
    private int carry = 0;

    /**
     * 求两个链表的和
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode sum1(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }

        if(l2 == null) {
            return l1;
        }

        int n1 = l1 == null ? 0 : l1.val;
        int n2 = l2 == null ? 0 : l2.val;

        // 计算两个节点l1和l2的值的和，并加上前一次计算可能的进位值carry
        int sum = n1 + n2 + carry;

        ListNode node = new ListNode(sum % 10);
        carry = sum / 10;

        node.next = sum1(l1 == null ? null : l1.next, l2 == null ? null :l2.next);

        return node;
    }




}















