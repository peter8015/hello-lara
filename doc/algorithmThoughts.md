## 
Understanding the "underlying logic" of these patterns is far more important than memorizing code. When you design an Enterprise AI system, you are essentially combining these paradigms to solve complex business pain points.
#### 1. Brute Force
- Logic: Exhausting every single possibility to find a solution.
- AI Context: Baseline model testing on small datasets or basic hyperparameter grid searches.
- Verdict: Simple but not scalable ($O(N^2)$ or higher). This is the first thing an Architect seeks to optimize.
#### 2. Greedy Algorithm
- Logic: Making the locally optimal choice at each step with the hope of finding the global optimum.
- AI Context:****LLM Decoding Strategies (Greedy Decoding). When generating text, the model simply picks the word with the highest probability at each step.
- Characteristic: Extremely fast, but not guaranteed to find the absolute best global solution.
#### 3. Divide and Conquer
- Logic: Splitting a large problem into independent sub-problems, solving them, and merging the results.
- **AI Context:**Distributed Training. Splitting a massive dataset across multiple GPU nodes, calculating gradients, and merging them at the end.
- Examples: Quick Sort, Merge Sort.
#### 4. Dynamic Programming (DP)
- Logic: Breaking a problem into overlapping sub-problems and storing intermediate results (Memoization) to avoid redundant calculations.
- AI Context:****Reinforcement Learning (RL). Calculating the optimal policy via state-transition equations.
- Examples: Kadane’s Algorithm (Maximum Subarray), Knapsack Problem.
#### 5. Backtracking
- Logic: A trial-and-error approach. If a path leads to a dead end, the algorithm "backtracks" to the previous step to try a different branch.
- **AI Context:**Multi-path planning for AI Agents. If an Agent finds that a specific tool call failed, it backtracks and tries an alternative sequence.
- Examples: N-Queens problem, Depth-First Search (DFS).
#### 6. Two Pointers & Sliding Window
- Logic: Using two indices (fast/slow or left/right) to move across an array or string, reducing the need for nested loops.
- AI Context:****Text Processing (Context Window). Extracting local features or tokens while sliding across a long document.
- Examples: Reversing a string, finding the longest substring without repeating characters.
#### 7. Search & Heuristics
- Logic: Exploring a vast search space using "rules of thumb" or evaluation functions to speed up the process.
- **AI Context:**A Search*, Monte Carlo Tree Search (MCTS) (The core logic behind AlphaGo).
#### 💡 The Architect’s Perspective: How to Choose?
As an Enterprise AI Architect, your design choices depend on your constraints:
- Need Low Latency? Prioritize Greedy or $O(1)$ optimized DP.
- Handling Massive Data? You must use Divide and Conquer combined with distributed computing.
- Handling Complex Logical Branches? Consider Backtracking combined with Heuristics.





