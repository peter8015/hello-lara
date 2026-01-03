> rule:
> 1. Record the problem desc.
> 2. Record the thoughts.

> prompt:
> Give thoughts for interview and divide into 3 steps, time complexity plus. 
# leetcode75 set colors
## problem
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

## thought:
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


# leetcode438. Find All Anagrams in a String
## problem
Give two string s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Example 1:
```Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```
## thought：

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

# leetcodde560. Subarray Sum Equals K
## problem
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2

## thought：
Core Mathematical PrincipleThe sum of a subarray can be expressed as the difference between two prefix sums.Assuming $Sum(i, j)$ represents the sum of the subarray from index $i$ to $j$, then:$$Sum(i, j) = PreSum[j] - PreSum[i-1]$$Our goal is to find the intervals where $Sum(i, j) = k$. This is equivalent to:$$PreSum[j] - PreSum[i-1] = k \implies \mathbf{PreSum[i-1] = PreSum[j] - k}$$


# leetcode53 Maximum Subarray
## problem
Given an integer of array nums, find the subarray with the largest sum, and return its sum
Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

## thought:
> Dynamic Programming (DP)

**Kadane's algorithm is a classic dynamic programming with greedy algorithm.**

Kadane’s Algorithm is essentially a **'Keep or Restart'** strategy. At every step, we ask: 
Is the previous running total helping me or hurting me? If it's a 'contributor' (positive), 
I keep it. If it’s a 'liability' (negative), I discard it and start fresh from the current element.

As we iterate through the array, we maintain a current_sum. For each new element, 
we have a choice: extend the existing subarray or start a new one. If the previous 
current_sum is positive, it's a 'contributor,' so we add the current element to it. 
However, if the current_sum has dropped below zero, it becomes a 'liability' that would 
only decrease our future potential. In that case, we discard it and reset our sum starting 
from the current element."

# LeetCode56.Merge Intervals — Medium
## problem
Given an array of intervals where intervals[i] = [starti, endi], 
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

## thought
Sorting+Greey
#### Step 1: Sort O(nlogn)
Sort the intervals by their start times. This ensures that overlapping intervals are neighbors, turning a messy problem into a simple, ordered list.
#### Step 2: Scan & Merge O(n)
Iterate through the list once. If the next interval starts before the current one ends, merge them by extending the end time. This is the Greedy part—making the best choice at each step.
#### Step 3: Finalize O(n)
If there’s no overlap, save the current interval to your results and start fresh with the new one. This keeps the process efficient and linear.
#### 💡 The "Pocket" Version (30-second summary)
> "I solve this in three steps: Sort the input to bring overlaps together, Scan the list while merging overlapping ranges greedily, and Output the finalized intervals. It’s an efficient $O(N \log N)$ solution."


# leetcode189.Rotate Array
Problem Description
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]

## algorithmic thought:
Introduce the Triple Reversal method. Use a small example to "walk" the interviewer through the logic.
- Say: "I can optimize this to $O(n)$ time and $O(1)$ space using a reversal strategy. For example, if we have [1,2,3,4,5] and $k=2$:"
    1. Reverse everything: [5,4,3,2,1]
    2. Reverse the first $k$ (2) elements: [4,5,3,2,1]
    3. Reverse the rest: [4,5,1,2,3] — Rotation complete.

