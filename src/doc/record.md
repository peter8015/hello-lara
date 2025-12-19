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