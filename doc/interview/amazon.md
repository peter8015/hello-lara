# scope

## algorithm approaches

* Hash Map
* Sliding Window
* Binary Search
* BFS / DFS
* Heap (Priority Queue)
* Graph Traversal
* Dynamic Programming
* Backtracking
* Tree Traversal
* System Design (for SDE II+)

## meta thoughts

- Decrease and Conquer
- Divide and Conquer
- Greedy
- Dynamic Programming
- Exhaustive Search & Backtracking

## include

### 1. Divide and Conquer 分治

对应：Binary Search、Merge Sort、快速排序等

覆盖：Binary Search

底层逻辑：把大问题拆成两半，解决子问题再合并结果。

### 2. Decrease and Conquer 减治

每次砍掉一部分问题规模（不是对半分）

典型：二分查找也可归减治、链表删除、有序数组查找

覆盖：Binary Search

### 3. Greedy 贪心

Heap、Sliding Window 大量场景依赖贪心

* Heap（TopK）：每次取最小 / 最大，贪心选择局部最优
* Sliding Window 部分最优解、区间问题、最小窗口
  覆盖：Hash Map、Sliding Window、Heap (Priority Queue)、Graph Traversal（Dijkstra）

### 4. Dynamic Programming 动态规划

完全一一对应列表里的 DP

覆盖：Dynamic Programming

### 5. Exhaustive Search & Backtracking 穷举 + 回溯

覆盖：Backtracking、Tree Traversal、DFS

DFS 本质是深度穷举；回溯是带剪枝的穷举；树遍历是特殊穷举。

## not include

* **BFS**

  BFS 是层级遍历思想，不属于分治 / 减治 / 贪心 / DP / 回溯穷举任何一类；

  BFS 核心是队列逐层扩散，和回溯 DFS 的深度穷举逻辑完全不同。
* **Graph Traversal（除贪心 Dijkstra 外的通用图遍历）**

  普通无加权图 BFS 遍历没有贪心、DP、分治特征，无法归入 5 种思想。

# dp

> 掌握这10道之后，LeetCode 中约 70% 的经典 DP 面试题都能归类到这些模板中。对于你目前准备 Amazon L5/L6、AWS Staff 或 AI Architect 面试，我建议把这 10 题做到：
>
> * 能在 10 分钟内写出最优解
> * 能解释状态定义
> * 能推导转移方程
> * 能分析时间复杂度
> * 能做空间优化

* Climbing Stairs (#70). ===
* House Robber (#198). ===
* Coin Change (#322). ====
* Word Break (#139)  ===
* Unique Paths (#62)
* Longest Common Subsequence (#1143)
* Edit Distance (#72)
* Partition Equal Subset Sum (#416)
* Longest Increasing Subsequence (#300)
* Longest Palindromic Substring (#5)
