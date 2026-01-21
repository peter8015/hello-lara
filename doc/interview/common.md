#### 1. Clarification & Constraints
Before you write any code, use these to define the boundaries.
- "Can I assume the input will always be valid, or should I handle null or empty cases?"
- "What is the expected range of the input size $n$? This will help me determine the target time complexity."
- "Are there any specific constraints on memory or space I should be aware of?"
- "Does the input contain duplicate values or negative numbers?"
#### 2. Discussing Strategy & Trade-offs
Use these phrases to show you are thinking about efficiency.
- "A brute-force approach would be to..." (Use this to show you have a starting point).
- "The bottleneck here is the nested loop, which makes the runtime $O(n^2)$."
- "To optimize this, we can use a HashMap to achieve $O(1)$ lookups."
- "I'm considering a trade-off: we can save time by using $O(n)$ extra space."
- "The key observation here is that the array is already sorted, which suggests a binary search approach."
#### 3. During the Coding Process (Thinking Out Loud)
Keep the interviewer engaged so they can help if you get stuck.
- "I'll start by initializing a few helper variables..."
- "I'm going to iterate through the array once and maintain a running sum."
- "Let me take a moment to organize my thoughts on this loop condition."
- "I'll break this down into a helper function to keep the code modular and clean."
- "I see a potential issue with the index bounds; let me double-check that."
#### 4. Testing & Handling Edge Cases
Always test your code verbally before saying "I'm finished."
- "Let's walk through a small example to dry-run the logic." (A "dry-run" means tracing the code with a sample input).
- "Now, let's consider an edge case, like when the input array has only one element."
- "I'll add a 'guard clause' at the beginning to handle the empty case."
- "If we reach the end of the loop without finding a match, we'll return -1 by default."
#### 5. Final Analysis
Always conclude with the Big O notation.
- "Overall, the time complexity is $O(n)$ because we only traverse the data once."
- "The space complexity is $O(1)$ since we are only using a constant amount of extra memory."
- "If $n$ grows very large, this approach will be much more scalable than the naive version."
#### Pro-Tip: When you are stuck
Don't stay silent. Use these "lifeline" phrases:
- "I'm currently thinking about how to handle the base case for this recursion."
- "I have a general idea of using a two-pointer technique, but I'm refining the logic for when to increment the right pointer."