package com.lara.arrayandlinkedlist;


import utils.ListNode;

/**
 * @author zhanghaibing
 * @date 2024-03-04
 */
public class ReverseKGroup {

    /**
     * deliberate practice
     * 25. K 个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     * <p>
     * 思路：
     * 1. 初始化: 创建一个哑节点（dummy node），其next指向原链表头节点。使用两个指针pre和end，初始时都指向哑节点。
     * 2. 遍历分组: 使用循环遍历链表，并通过内层循环找到每k个一组的子链表的结束位置（即end）。
     * 3. 反转子链表: 当找到包含k个节点的子链表时，记录子链表的开始位置（即start）和子链表结束后紧跟的下一个节点（即next）。
     *      然后，断开子链表与剩余部分的连接，通过递归调用reverse(start)方法反转子链表。
     * 4. 恢复连接: 反转后的子链表头部成为新的起始节点，将其连接回原链表剩余部分：start.next = next；同时更新pre指针指向反转后的新起始节点。
     * 5. 迭代继续: 更新end指针到新的位置，以便处理下一组节点。
     *
     * 此解法的主要思想是通过不断寻找并反转每k个一组的子链表来逐步完成整个链表的翻转操作，同时利用哑节点简化边界条件处理，确保整个过程能够正确地处理任何长度和情况的链表。
     *
     * 该方法的时间复杂度主要由以下部分组成：
     * 查找k个节点的子链表结束位置：通过一个for循环完成，循环次数最多为k次。因此这部分的时间复杂度为O(k)。
     * 反转子链表：在原代码中使用递归的reverse(start)方法，其时间复杂度为O(k)，因为需要遍历并翻转整个包含k个节点的子链表。
     * 主循环：在链表中迭代，每次迭代至少处理一组（k个）节点，直到链表末尾。由于链表长度为n，且每次操作一组k个节点，所以总共执行的操作次数大约是n / k次。因此这部分的时间复杂度为O(n/k)。
     * 综合以上分析，该算法总的时间复杂度为O(n + n/k)，因为反转每个子链表所需的时间相对于总的链表长度来说是可以忽略不计的，最终我们可以将时间复杂度简化为O(n)。这意味着对于给定的链表长度和分组大小k，算法整体运行时间与链表长度线性相关。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. 初始化: 创建一个哑节点（dummy node），其next指向原链表头节点。使用两个指针pre和end，初始时都指向哑节点。
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;
        // 2. 遍历分组: 使用循环遍历链表，并通过内层循环找到每k个一组的子链表的结束位置（即end）。
        while (end.next != null) {
            // 内层循环用于查找包含k个节点的子链表的结束位置（end）
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;

            // 3. 记录当前待反转子链表的起始位置（start）和反转后需要连接的下一个节点（next）
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;

            // 4. 反转子链表：调用reverse方法对从start开始的子链表进行翻转，并更新pre.next指向翻转后的子链表头部
            pre.next = reverse(start);
            start.next = next;

            // 更新pre指针，使其指向已反转子链表的新头部，以便在下一次循环中继续处理新的子链表
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }

        // 创建初始节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        // 遍历链表，k个一组
        while(end != null) {
            for(int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if(end == null) break;

            // 记录反转前的位置 1
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;

            pre.next = reverse(start);
            start.next = next;

            // 更新pre指针 2
            pre = start;
            end = pre;
        }
        return dummy.next;
    }
}



