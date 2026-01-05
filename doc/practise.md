# leetcode198. Rotate Array 
In a coding interview, the way you communicate is just as important as the code itself. Interviewers look for your "algorithmic thinking" process.
Here is the step-by-step framework to handle Rotate Array (or any algorithm) like a pro:
#### Step 1: Clarify and Confirm (The "Edge Case" Phase)
Before typing a single line, ask clarifying questions. This shows you are a thorough engineer.
- Ask: "Can $k$ be larger than the array length?" (Wait for: "Yes.")
- Ask: "Should I modify the array in place, or is returning a new array acceptable?" (Wait for: "Try to do it in place.")
- Confirm: "If the array is empty or has one element, I assume I can just return it as is, correct?"
#### Step 2: Discuss the Brute Force (The "Baseline")
Briefly mention the simple solution to show you understand the problem, but immediately explain why it's not optimal.
- Say: "A naive approach would be rotating the array one step at a time, $k$ times. However, that would take $O(n \cdot k)$ time, which is inefficient for large inputs."
#### Step 3: Propose the Optimal Strategy (The "Pitch")
Introduce the Triple Reversal method. Use a small example to "walk" the interviewer through the logic.
- Say: "I can optimize this to $O(n)$ time and $O(1)$ space using a reversal strategy. For example, if we have [1,2,3,4,5] and $k=2$:"
    1. Reverse everything: [5,4,3,2,1]
    2. Reverse the first $k$ (2) elements: [4,5,3,2,1]
    3. Reverse the rest: [4,5,1,2,3] — Rotation complete.
#### Step 4: Write Clean Code (The "Implementation")
Write the code while explaining it. Use meaningful names and handle the $k = k \pmod n$ logic.
Java
```public void rotate(int[] nums, int k) {
    if (nums == null || nums.length <= 1) return;

    int n = nums.length;
    k %= n; // Crucial for when k > n

    // 1. Reverse the whole array
    reverse(nums, 0, n - 1);
    // 2. Reverse the first k elements
    reverse(nums, 0, k - 1);
    // 3. Reverse the remaining n - k elements
    reverse(nums, k, n - 1);
}

private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start++] = nums[end];
        nums[end--] = temp;
    }
}

```
#### Step 5: Complexity Analysis (The "Verification")
Explicitly state the Big O notation.
- Time Complexity:$O(n)$. We visit each element a constant number of times (twice).
- Space Complexity:$O(1)$. We are performing the rotation in place without extra data structures.
#### Step 6: Test with an Example (The "Dry Run")
Take a simple case and trace it.
- Interviewer: "What if $k=0$?"
- You: "Since 0 % n is 0, the first reverse happens, then the second reverse is (nums, 0, -1) which doesn't execute the loop, and the third is (nums, 0, n-1) which flips it back to original. The logic holds."
#### Interviewer "Green Flags" to show off:
1. Modulo Operator: Don't forget k %= n. It's the most common mistake.
2. Helper Method: Creating a separate reverse() function makes your code cleaner and more modular.
3. Boundary Check: Mentioning null or length < 2 shows you think about stability.
