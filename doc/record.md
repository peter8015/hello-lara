
# 75 set colors
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

The Dutch National Flag Algorithm is a popular programming technique used to sort an array containing three distinct values (like 0s, 1s, and 2s). It was designed by Edsger Dijkstra to solve the problem of partitioning elements in a single pass.
#### 🧱 The 3-Pointer Strategy
The algorithm maintains three pointers to divide the array into four conceptual sections:
1. low: Tracks the boundary for the first element (e.g., 0). Everything before low is 0.
2. mid: The current element being scanned. Everything between low and mid-1 is 1.
3. high: Tracks the boundary for the last element (e.g., 2). Everything after high is 2.
4. mid to high: The "unknown" territory that hasn't been sorted yet.
#### 🕹️ How the "Engine" Works
The algorithm runs a single while loop as long as mid <= high. Inside, it follows these rules:
- If nums[mid] == 0:
    - It belongs in the "red" (low) section.
    - Swap nums[low] and nums[mid].
    - Move both low and mid forward ( low++, mid++).
- If nums[mid] == 1:
    - It belongs in the middle section.
    - Just move mid forward ( mid++).
- If nums[mid] == 2:
    - It belongs in the "blue" (high) section.
    - Swap nums[mid] and nums[high].
    - Move high backward ( high--).
    - Crucial: Do not move mid yet, because the new value swapped from the end needs to be inspected.
#### 🚀 Why Is It So Efficient?
1. Time Complexity: $O(n)$
    - You only pass through the array once. Each element is visited or swapped at most twice.
2. Space Complexity: $O(1)$
    - It is an in-place algorithm. You don't create a new array; you just move elements around in the existing one.
3. Optimal for Duplicate Values
    - Standard sorting algorithms like QuickSort take O(nlogn). This algorithm is much faster for cases where there are only a few unique values repeated many times.


# 438. Find All Anagrams in a String

Give two string s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Example 1:
```Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```
thought：

1. Pre-computation & Frequency Setup
   First, establish a reference frequency map (using a size-26 integer array) to store the character counts of string p. This serves as the "DNA" or signature that any valid anagram must match. At the same time, initialize an empty window array to track characters in the current segment of string s.

2. Fixed-Size Sliding Window (In & Out)
   Iterate through string s using a right pointer to expand the window. For every step:

    Inbound: Add the new character at right to the window frequency map.

    Outbound: If the window width exceeds the length of p, remove the character at the left pointer from the map and increment left. This ensures the sliding window always maintains a constant width equal to the target string p.

3. State Comparison & Result Logging
   In each iteration (once the window is full), compare the window map against the target map.

    Validation: If the two arrays are identical (Arrays.equals), it proves the current substring is a valid anagram.

    Collection: Record the left index into the result list and continue sliding until the right pointer reaches the end of the string.

# 560. Subarray Sum Equals K
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2

## thought：
- 前缀和 + 哈希表为了将时间复杂度降到 $O(n)$，我们通常使用前缀和 (Prefix Sum) 结合 哈希表 (HashMap) 的技巧。

- 核心思想：如果在索引 i 处的前缀和为 preSum[i]，在索引 j 处的前缀和为 preSum[j]，那么子数组 nums[i+1...j] 的和就是 preSum[j] - preSum[i]。我们要找的就是满足 preSum[j] - preSum[i] == k 的组合，等价于查找哈希表中是否存在 preSum[j] - k。