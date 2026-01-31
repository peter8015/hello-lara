> common use language

# 1
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

# 2

#### 第一阶段：理清需求 (Clarification)

在动手写代码前，先确保没有漏掉隐含条件。

- 确认输入类型： "What kind of data are we dealing with? Are they all integers, or could there be floating-point numbers?"
- 确认数据规模： "What is the expected size of the input? This will help me decide between an $O(n^2)$ or $O(n \log n)$ approach."
- 确认特殊情况： "How should I handle edge cases, such as an empty array, null input, or a single element?"
- 关于 0 或负数： "Are there any constraints on the values? Can they be negative or zero?"
- Just to confirm
- Before I dive into the solution, I'd like to clarify a few things.
- Is it possible for the array to contain any zeros?
- Are there any edge cases, like an empty array or just a single element? Or can I follow the constraints that n is at least 2?

#### 第二阶段：沟通思路 (Communication of Strategy)

在写代码前，先口述你的逻辑，得到面试官的认可。

- 提出初步想法： "A naive approach would be to use nested loops, but that would take $O(n^2)$ time. I think we can optimize it."
- 提出优化方案： "To improve the performance, I'm thinking of using a HashMap (or Two Pointers / Sliding Window) to bring the time complexity down to $O(n)$."
- 权衡利弊： "There is a trade-off here: we can save time by using more memory, or vice versa."
- 寻求反馈： "Does this approach sound reasonable to you? / Does that make sense?"

#### 第三阶段：开始编码 (Coding)

边写边说，不要让空气突然安静。

- 声明变量： "I'll start by initializing a result array and a variable to keep track of the current sum."
- 解释循环： "I'm going to iterate through the array once..."
- 处理边界： "Let me add a sanity check (or guard clause) here to handle null inputs."
- 编写中： "Now I'll implement the core logic inside this loop."

#### 第四阶段：检查与测试 (Dry Run & Testing)

写完后不要立刻说“我写完了”，先自己人肉跑一遍。

- 自我检查： "Let me walk through an example to see if this logic holds up."
- 发现错误并修正： "Oh, I see a small bug here. This should be i < n - 1 instead of i < n. Let me fix that real quick."
- 分析复杂度： "So, the time complexity of this solution is $O(n)$ because we only traverse the array twice, and the space complexity is $O(1)$."

#### 第五阶段：后续讨论 (Follow-up)

针对面试官的追问。

- 询问优化建议： "Is there any particular part of the code you'd like me to optimize further?"
- 讨论可读性： "In a real production environment, I might break this down into smaller helper functions for better readability."
- 如果卡住了： "I'm currently thinking about how to handle [specific issue]. I've tried [A], but I'm wondering if [B] might be more efficient." (这样说比沉默好得多)

#### 💡 几个万能的小短语：

- "Trade-off": 权衡（面试官最爱听的词，体现工程思维）。
- "Brute force": 暴力解法（通常作为思考的起点）。
- "On the fly": 实时/动态地（例如：Updating the sum on the fly）。
- "Corner cases": 极端/边缘情况。
- "Space-time complexity": 时空复杂度。



# new

#### Step 1: Clarify and Confirm
> Before I dive into the code, I'd like to clarify a few things: input, output, scale
- Can the input array be null or empty? And what should be returned in these cases?
- What about array with only one element? Should it return 1 in that case?
- What's the maximum size of the input array? (to understand the scale)
- Confirming that the solution needs to achieve O(n) time complexity as per the requirement?

#### Step 2: Discuss the Brute Force (The "Baseline")
> A naive approach would be ... which would take O(n) time. However, the requirement is to solve this in O(n) time.
> The baseline would be to ...
(A naive approach would be to sort the array first and then find the longest consecutive sequence, which would take O(n log n) time. However, the requirement is to solve this in O(n) time.)

The baseline would be to compare every string against every other string. For each pair, we'd check if they are anagrams by counting characters.

#### Step 3: Propose the Optimal Strategy (The "Pitch")

To improve efficiency, we use a **HashMap** to group strings by their "Signature."

#### Step 4: Write Clean Code (The "Implementation") (add todo)

I’ll implement the sorting approach for its readability, using modern Java idioms for conciseness.

#### Step 5: Complexity Analysis (The "Verification")

**Time Complexity: O(N \* K log K)**

* **N** is the number of strings.
* **K log K** is the cost of sorting each string of length K.
* **Map operations** (put/get) are O(1) on average.

**Space Complexity: O(N \* K)**

* We store every character of every string in the HashMap.
