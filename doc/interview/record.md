

> record all the algorithms 

- rule:
  - Record the problem desc.
  - Record the thoughts.

- prompt:
  - As a candidate, what should I say in an algorithm interview: code
  - Give thoughts for interview and divide into 3 steps, time complexity plus.
  - Rewrite it to fit Google algorithm interview(step1,2,3,4).
  - So complex, simplify it.
  - Assume you are an L6 interviewer, what do you want to hear

- words
Summary Checklist for your narration:
  - "I'm choosing [Data Structure] because..."
  - "I'm naming this [Name] to reflect..."
  - "I'm handling the edge case of [X] by..."
  - "I'm abstracting this because..."
  - "In a real system, I would also add [Logging/Validation/Tests] here."

# template 
prompt: 
Prompted 把上面 的几步修改为面试时候选人的真实英文表达,使用简单词汇 
Assume you are a Google L6 candidate, answer LeetCode problem 53. Maximum Subarray with the following template. Template
### Which algorithm of meta thoughts does it belong to?
### Step 1: Clarify and Confirm
### Step 2: Discuss the Brute Force (The "Baseline")
### Step 3: Propose the Optimal Strategy (The "Pitch")
### Step 4: Write Clean Code (The "Implementation") (add todo)
### Step 5: Complexity Analysis (The "Verification")


# commons
- liner pass
- 

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
#### Meta-Thought / High-Level Approach
"This is a classic Dynamic Programming problem. The core question is: can we determine the optimal sum at the current index $i$ by looking only at the result from $i-1$ and the current value? Since we only care about the 'best so far,' we can solve this using Kadane’s Algorithm in a single linear pass."
#### Step 1: Clarify and Confirm (The "Discovery" Phase)
"Before I jump into the solution, I’d like to clarify a few constraints:
- What's the minimum size of the array?
- Does subarray must be contiguous?
- Does the array contains negative numbers? First, if the array contains only negative numbers, should I return the largest single element? I'll assume yes.
- Second, do you want just the maximum sum, or do you also need the start and end indices of the subarray? I’ll start with the sum for now.
- Finally, regarding the input scale: for a $10^5$ sized array, I should aim for an $O(N)$ time complexity to avoid a timeout."

#### Step 2: Discuss the Brute Force (The "Baseline")
A naive approach would be to use nested loops to traverse the array. That would result in $O(n^2)$ time complexity or worse."
"A naive approach would be to check every possible subarray. We could use two nested loops to pick a start and an end point, then sum the elements between them. However, that would be $O(N^2)$ or even $O(N^3)$. With $10^5$ elements, that's billions of operations—definitely too slow for a production environment. We need a more efficient, linear approach."
#### Step 3: Propose the Optimal Strategy (The "Pitch")
"I propose using Kadane’s Algorithm. The intuition is quite simple:
As we iterate through the array, we have a choice at each step:
1. Either we extend the previous subarray by adding the current number.
2. Or we start a new subarray beginning at the current number.
   The rule is: if the sum we’ve built up so far becomes negative, it’s only going to 'drag down' the next number. In that case, we should discard the past and start fresh. We'll keep a global 'best' variable to track the highest sum we've seen during the entire process."
#### steo 4：clean code
"Okay, let's code this. I'll start with the first number to handle the negative cases. (写下 max_so_far = nums[0])
Inside the loop, I’ll decide whether to continue the current subarray or start over. (写下 max_current = max(...))
I'm using simple names so the logic is clear. Also, notice I only use two variables, which is very memory-efficient."
#### Step 5: Complexity Analysis (The "Verification")
"To verify the efficiency:
- Time Complexity is $O(N)$: We visit each element exactly once, performing constant-time operations inside the loop.
- Space Complexity is $O(1)$: We aren't using any extra data structures that grow with the input. We only store two scalar variables, which makes this very memory-efficient.

### what to say with coding
#### 1. The Setup & Edge Cases
Don't just jump into the loop. Start by clarifying the problem and handling "bad" input.
- What to say:"First, I’ll handle the edge cases. If the array is null or empty, the operation is undefined, so I’ll throw an exception. If there’s only one element, that element is by definition the maximum subarray, so I can return it immediately to save processing time."
#### 2. Introducing the Logic (Kadane’s Algorithm)
Before you write the loop, name the approach. It shows you know the "why" behind the "how."
- What to say:"To solve this in linear time, I’m going to use Kadane’s Algorithm. The core idea is that for every element, we decide whether to 'restart' the subarray at that element or 'extend' the existing subarray."
#### 3. Explaining the Loop (The Decision Point)
This is the most important part to explain clearly.
- What to say:"I’ll initialize both maxSum (our global best) and currSum (our local best) to the first element. As I iterate through the array, the logic at each step is:
    - currSum = Math.max(nums[i], currSum + nums[i]): Should I add the current number to the sum I already have, or is the current number itself actually bigger than the entire sum combined? If the current number is better on its own, we restart the subarray there.
    - maxSum = Math.max(currSum, maxSum): Then, I simply update the global maximum if our current local sum has surpassed it."
#### 4. Complexity Analysis
Always finish by stating the Big O notation without being asked.
- What to say:"The time complexity is $O(n)$ because we only iterate through the array once. The space complexity is $O(1)$ because we are only using two integer variables regardless of the input size."

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

# leetcode160 Intersection of Two Linked Lists
## problem
Given the heads of two singly linked-list, return the node at which two list intersect.
* If the two linked lists have no intersection at all, return null.
*
* Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
* Output: Intersected at '8'

## os
### Why is this "Decrease and Conquer"? Decrease by a Variable Size (or "Reduction")
You are decreasing the complexity of the "offset" (the difference in length) by forcing both pointers to travel the exact same total distance. You turn a coordination problem into a simple iteration.
LeetCode 160 (The Two-Pointer approach) belongs to the Decrease by a Variable Size (or "Reduction") category of the Decrease and Conquer meta-thought.
Here is why:
#### 1. The Strategy: Reducing the "Offset"
The core difficulty of this problem is the difference in length (the offset) between the two lists.
- In the first pass, the pointers are out of sync.
- By switching heads, you are decreasing the unknown offset to zero.
- Once the offset is "conquered" (reduced to 0), the problem is reduced to a simple simultaneous traversal.
#### 2. Why it fits "Variable Size"
Unlike Binary Search (which always cuts the problem in half) or Insertion Sort (which always reduces the size by 1), the amount of "progress" made toward alignment in this algorithm depends on the lengths of the lists (M and N). You are reducing the search space through a mathematical trick rather than a fixed division.

### Step 1: Clarify and Confirm 
#### 1. Functional Clarification
- Intersection by Identity, not Value: "To confirm, we are looking for the intersection based on reference (memory address), correct? If two different nodes happen to have the same value but different memory addresses, that does not count as an intersection."
- Structure Preservation: "Is it safe to assume the original list structure should remain unchanged after the function returns? (Usually, the answer is yes, which rules out destructive solutions like reversing the lists)."
- Cycle-Free: "Can I assume both lists are acyclic? If there were a cycle within one of the lists, the intersection logic would need to change significantly."
#### 2. Constraints & Scale (The "Staff" Edge)
- Time/Space Complexity: "My goal is to achieve $O(m + n)$ time complexity. For space, are we aiming for $O(1)$? A hash-set approach would be $O(n)$ space, but I suspect we want a constant space solution."
- List Lengths: "Is there a significant difference in scale? If one list is millions of nodes and the other is five, does that change our memory constraints?"
#### 3. Edge Case Matrix
Scenario	Expected Output	Logic
No Intersection	null	The pointers should both hit null without matching.
Identical Lists	headA (or headB)	They intersect at the very first node.
One List is Null	null	Immediate exit; no intersection possible.
Lists of Different Lengths	Intersection Node	This is the core challenge (handled by the two-pointer swap).
Intersection at Tail	Tail Node	The very last node is the only shared element.
### Step 2: Discuss the Brute Force (The "Baseline")
"The simplest way is to use a Hash Set. I'd store all the nodes from List A in the set, then walk through List B. The first node I find that's already in the set is the intersection.
- Why it's okay: It's fast ($O(N+M)$).
- Why it's not perfect: It uses extra memory ($O(N)$). At Google scale, we prefer $O(1)$ memory."
### Step 3: Propose the Optimal Strategy (The "Pitch")
"Imagine two runners on two different paths that eventually merge. Path A is short, Path B is long. To make them meet at the merge point, have them swap paths once they finish their first lap."
- The Logic:
    1. Pointer A walks List A, then List B.
    2. Pointer B walks List B, then List A.
- The Result: Both will have walked exactly the same total distance ($A + B$). Because they travel the same total length, they must collide at the intersection point during their second lap.
### Step 4: Write Clean Code (The "Implementation")
### Step 5: Complexity Analysis (The "Verification")
Time: $O(N + M)$ — Each pointer walks at most two lists.Space: $O(1)$ — No extra sets or maps. Just two variables.


