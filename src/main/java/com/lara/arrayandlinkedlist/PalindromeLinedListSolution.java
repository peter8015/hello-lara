package com.lara.arrayandlinkedlist;


import utils.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode234. Palindrome Linked List
 * Given a head of a singly linked list, return true if
 * it is a palindrome. return false otherwise.
 *
 * @author zhanghaibing
 * @date 2024-03-08
 */
    public class PalindromeLinedListSolution {

    public boolean isPalindromeLinkedListx(ListNode head) {
        // 1. 链表值加入到数组
        List<Integer> arr = new ArrayList();

        while(head != null) {
            arr.add(head.val);
            head = head.next;
        }

        // 2. 双指针遍历数组
        int start = 0, end = arr.size() - 1;

        while(start < end) {
            if(arr.get(start) != arr.get(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    /**
     * time O(n) space O(n)
     * 1. Can the linked list be null? If yes, return true?
     * If the linked list only one node, return true?
     * 2. A navie approach is to nested iterate through the linked list. That would be O(n^2) time.
     * 3. We can use two pointers and an Arraylist to iterate through the linked list simultaneously.
     * 4. The time complexity is O(n) abd the space complexity is O(n)
     */
    public boolean isPalindromeLinkedList(ListNode head) {
        // edge case
        if(head == null || head.next == null) return true;

        // store node to list
        List<Integer> data = new ArrayList();
        while(head != null) {
            data.add(head.val);
            head = head.next;
        }

        // use two pointer
        int start = 0, end = data.size() - 1;
        while(start < end) {
            if(data.get(start) != data.get(end)) return false;
            start++;
            end--;
        }

        return true;
    }
}
