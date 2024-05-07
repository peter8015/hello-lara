package com.lara.arrayandlinkedlist;

import utils.ListNode;

/**
 * @author zhanghaibing
 * @date 2024-02-19
 */
public class SortList {

    /**
     * leetcode 148 排序链表
     * 给你链表的头节点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * <p>
     * 思路：归并排序对于链表排序来说是一种非常有效和稳定的方法。
     * <p>
     * 解题思路：
     * 分解：首先，将链表一直对半分割，直到链表只剩下一个或两个节点。这是递归的基础情况。
     * 递归解决：对于每个子链表，递归地应用归并排序。这意味着对每个子链表继续分解，直到达到基础情况，然后合并它们。
     * 合并：合并两个已排序的子链表。这是归并排序的关键步骤。你需要创建一个新链表，然后比较两个子链表的头节点，将较小的节点添加到新链表中，
     * 并移动相应链表的头指针。重复此过程，直到一个链表为空，然后将另一个链表的剩余部分添加到新链表的末尾。
     * 时间复杂度分析：
     * 链表对半分割：使用快慢指针法找到链表的中点，时间复杂度为O(n)，其中n为链表的长度。
     * 子链表的递归排序：每次将链表一分为二，递归调用自身进行排序。最坏情况下（完全逆序），整个过程类似二叉树的完全分解，深度为log_2(n)，每层需要进行一次O(n)的分割操作，因此这部分的时间复杂度为O(n log n)。
     * 有序子链表的合并：假设已有merge方法能将两个有序链表合并成一个有序链表，且时间复杂度为O(m + n)，其中m和n分别为两个链表的长度。在本问题中，每次合并的是两个大小大致相等的子链表，所以合并操作的时间复杂度可以视为O(n)。
     * <p>
     * 易错点：
     * 1. 合并链表时，两个链表为空的情况
     * 2. 利用快慢指针找到中间节点的方法。
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 递归将链表对半分割，直到只剩下一个或两个节点
     *
     * @param head
     * @param tail
     */
    public ListNode sortList(ListNode head, ListNode tail) {
        // 链表为空直接返回
        if (head == null) {
            return head;
        }

        // 只有两个节点的链表，调整顺序返回
        if (head.next == tail) { //??
            head.next = null;
            return head;
        }

        // 使用快慢指针找到中间节点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        // 递归对左右两部分进行排序
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        // 合并已排序的链表
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = l1, temp2 = l2;

        // 链表都不为空时合并链表
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        // 两链表其中一个为空时，直接将另外一个链表连接到新链表的末尾
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }

        return dummy.next;
    }


    public ListNode sortListx(ListNode head, ListNode tail) {

        // head 为空直接返回
        if (head == null) {
            return head;
        }

        // 有两个节点时
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 进行归并排序并合并两个有序链表
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;

        ListNode left = sortListx(head, mid);
        ListNode right = sortListx(mid, tail);

        return mergex(left, right);
    }

    public ListNode mergex(ListNode left, ListNode right) {
        // 定义首节点
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = left, temp2 = right;

        // 两个链表不为空的情况下，对比值，合并链表
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        // 两个链表其中一个为空的情况
        if (temp1 == null) {
            temp.next = temp2;
        }
        if (temp2 == null) {
            temp.next = temp1;
        }
        return dummy.next;
    }

}



























