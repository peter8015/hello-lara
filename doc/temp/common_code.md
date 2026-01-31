> [TOC] equals cmd+7

# catlog

- 常用代码片断
- 常用心法

# 常用心法

- 高手心法：在刷数组题时，一定要养成先判断数组是否有序的习惯。
  - 如果有序 $\rightarrow$ 优先考虑 二分查找 或 双指针。
  - 如果要求子数组和 $\rightarrow$ 优先考虑 前缀和 或 滑动窗口。

# 常用代码片断

## reverse array

```java
// reverse in-place
public void reverse(int[] nums, int start, int end) {
    while(start < end) {
        int temp = nums[start];
        nums[start++] = nums[end];
        nums[end--] = temp;
    }
}

```

## 二分查找

```java
pwublic int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2; // 防止溢出
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

## 滑动窗口

```java
int left = 0, right = 0;
while (right < nums.length) {
    // 1. 【入窗】：窗口扩大，加入 nums[right]，更新窗口状态
    right++;
  
    while (窗口需要收缩) {
        // 2. 【出窗】：窗口缩小，移除 nums[left]，更新状态
        left++;
    }
}
```

## 反转单链表 (Reverse Linked List)

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next; // 暂存后继
        curr.next = prev;              // 反转指针
        prev = curr;                   // 前驱后移
        curr = nextTemp;               // 当前节点后移
    }
    return prev;
}
```

## BFS

```java
public void bfs(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size(); // 当前层的节点数
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

}
```

## 快速幂

> 当你需要求 $a^n \pmod m$ 时，普通的循环会超时，这是 $O(\log n)$ 的解法。

```java
public long fastPow(long base, long n) {
    long res = 1;
    while (n > 0) {
        if (n % 2 == 1) res *= base;
        base *= base;
        n /= 2;
    }
    return res;
}
```

## 6. 快慢指针判断环 (Floyd's Cycle)

```java
public void hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true; // 相遇说明有环
    }

}
```

## 7. 回溯算法模板 (Backtracking)

解决排列组合、子集问题的通用框架。

```java
void backtrack(路径, 选择列表) {
    if (满足结束条件) {
        result.add(路径);
        return;
    }
    for (选择 : 选择列表) {
        做选择;
        backtrack(路径, 选择列表);
        撤销选择; // 这一步是核心
    }
}
```

## 9. 优先队列 Top K (PriorityQueue)找最大/最小的 $k$ 个元素，不需要全局排序。

```java
public List<Integer> topKFrequent(int[] nums, int k) {
    // 小顶堆，保持堆的大小为 k
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int num : nums) {
        pq.offer(num);
        if (pq.size() > k) pq.poll();
    }

}
```

## 荷兰国旗问题 (Three-Way Partition)

> 应用场景：将数组按某个基准分为三部分（小于、等于、大于）。常用于快速排序优化或颜色分类。

```java
public void sortColors(int[] nums) {
    int p0 = 0, curr = 0, p2 = nums.length - 1;
    while (curr <= p2) {
        if (nums[curr] == 0) { // 情况1: 放到左边
            swap(nums, p0++, curr++);
        } else if (nums[curr] == 2) { // 情况2: 放到右边
            swap(nums, curr, p2--); // 注意：这里curr不自增，因为换回来的数还没检查
        } else { // 情况3: 留在中间
            curr++;
        }
    }

}
```

# 心法

#### 1. 看到“最短/最少”，先想 BFS；看到“所有可能”，先想 DFS/回溯

这是区分算法逻辑最快的方法：

- BFS (广度优先)：像涟漪一样扩散。如果题目问“达到目标最少需要几步”、“最短距离是多少”，通常用 BFS。
- DFS (深度优先/回溯)：像走迷宫。如果题目问“请列出所有可能的组合”、“一共有多少种走法”，通常用回溯。

#### 2. 看到“前 K 个”或“中位数”，先想 堆 (PriorityQueue)

- 如果你需要实时维护一组数据的最大值或最小值，或者找第 K 大的数，不要每次都全排序（$O(N \log N)$），用堆可以优化到 $O(N \log K)$。
- 技巧：求前 K 大，用小顶堆（把小的踢出去，剩下的就是大的）；求前 K 小，用大顶堆。

#### 3. 看到“连续子数组”且元素皆为正数，先想 滑动窗口

- 滑动窗口是处理数组子区间问题的“神技”。2
- 心法：右指针不停向右动（寻找可行解），左指针在满足条件时向右动（优化可行解）。

#### 4. 看到“子问题重复计算”，必用 动态规划 (DP) 或 备忘录

- 如果你发现计算 $f(5)$ 的时候需要先算 $f(4)$ 和 $f(3)$，而算 $f(4)$ 时又要算一遍 $f(3)$，这就是重叠子问题。
- 心法：不要直接递归，要么用数组存结果（自底向上 DP），要么用 HashMap 存结果（自顶向下记忆化递归）。

#### 5. 空间换时间：万能的 HashMap

- 几乎 50% 的算法优化方案，第一步都是“能不能用空间换时间”。
- 心法：当你需要频繁查找某个值是否存在，或者记录元素出现的次数时，第一时间祭出 HashMap 或 HashSet，将查找复杂度从 $O(N)$ 降到 $O(1)$。

#### 6. 面试中的“作弊”直觉（经验总结）

当你实在没有思路时，可以根据数据规模反推算法：

- $N \le 20$：可能是极其复杂的回溯或状态压缩。
- $N \le 10^3$：可能是 $O(N^2)$ 的算法，比如双重循环 DP。
- $N \le 10^5$：必须是 $O(N \log N)$ 的算法，通常是排序、二分或分治。
- $N \le 10^7$：必须是 $O(N)$ 的算法，通常是一次遍历、双指针或哈希。

#### 7. 区间不重叠问题，先 排序

- 处理“会议室预约”、“合并区间”、“插入区间”这类题，第一步 100% 是按起点（或终点）排序。
- 排序之后，问题就变成了简单的线性比较（上一个的结束时间 vs 下一个的开始时间）。

#### 8. 链表题的“救命稻草”：虚拟头节点 (Dummy Node)

- 写链表题（比如删除节点、合并链表）最痛苦的是处理 head 为空的情况。
- 心法：创建一个 ListNode dummy = new ListNode(0); dummy.next = head;，然后返回 dummy.next。这样你就不需要写一堆 if (head == null) 的判断了。

#### 9. 看到“有序”二字，肌肉反应应是 二分查找

- 不管题目是在找数，还是在找某种最优的边界值，只要有单调性（有序），就一定要往二分（Binary Search）上靠。
