This is a highly systematic synthesis. To help you demonstrate the architectural depth expected at the L6 (Staff Level) during interviews, I have integrated the five meta-strategies, interview "power phrases," algorithmic explanations, and their applicable scenarios into a comprehensive guide.
#### 🧠 The Master Map of Algorithm Interview Meta-Strategies (L6 Senior Edition)
#### The Five Meta-Strategies: One-Sentence Core
- Decrease and Conquer: Leverage structural properties (like monotonicity or symmetry) to eliminate irrelevant options, reducing the original problem to a smaller version of itself.
- Divide and Conquer: Decompose a complex problem into multiple independent sub-problems, solve them separately, and aggregate them into a global solution.
- Greedy: Make the locally optimal choice at each step, relying on specific problem properties to ensure these choices lead to a global optimum.
- Dynamic Programming: Store results of sub-problems to avoid redundant calculations in sequences with overlapping sub-structures using state transitions.
- Exhaustive Search & Backtracking: Systematically explore all possible paths in a state space and "roll back" steps when a branch fails to meet constraints.
#### 1. Decrease and Conquer
- Interview Power Phrase:"I can reduce the problem's complexity by leveraging its inherent structure to eliminate unnecessary states."
- Core Algorithms:
    - Triple Reverse: Achieves in-place array re-partitioning in $O(1)$ space by reversing the global order and then restoring local segments.
    - Binary Search: Eliminates half of the search space at each step by leveraging data monotonicity.
    - Monotonic Stack: Maintains a sorted stack to instantly locate the first greater/smaller element to the left or right.
    - Quick Select: Uses the QuickSort partitioning logic to find the $k$-th largest element by only entering one partition.
- Key Scenarios: Array rotation, ordered search, Trapping Rain Water, Top-K problems.
- Application Logic:
    - Array/String: In-place rotation, reversing word order, finding the minimum in a rotated array.
    - Monotonic Stack: "Next Greater Element" problems (e.g., daily temperatures, trapping rain water).
    - Heap: Dynamically retrieving extrema, handling Top-K elements in data streams.
#### 2. Divide and Conquer
- Interview Power Phrase:"I'll decompose the global problem into independent sub-problems and aggregate their results during the post-order phase."
- Core Algorithms:
    - Merge Sort: Splits the array in half, sorts recursively, and merges them in linear time.
    - Tree Recursion: Processes left and right subtrees recursively, bubbling up info (height, paths) to the parent.
    - Quick Sort: Selects a pivot to divide data into two independent partitions for recursive processing.
- Key Scenarios: Almost all binary tree problems, large-scale data sorting, inversion counting.
- Application Logic:
    - Tree: Diameter of a tree, checking balance, path sums.
    - Linked List: Sorting a linked list (Merge Sort).
    - Array: Counting inversions in an array.
#### 3. Greedy
- Interview Power Phrase:"A greedy strategy is optimal here because the local best choice consistently leads to a global optimum without violating constraints."
- Core Algorithms:
    - Dutch National Flag: Uses three pointers in a single pass to physically partition elements into designated zones.
    - Sliding Window: Maintains a dynamic boundary to optimize $O(n^2)$ contiguous subarray problems to $O(n)$.
    - Two Pointers: Uses two ends of a sequence to converge toward a specific target or find a midpoint.
    - Dijkstra: Selects the node with the shortest current distance for relaxation to build a shortest-path tree.
- Key Scenarios: Contiguous subarray extrema, cycle detection, interval merging, shortest paths.
- Application Logic:
    - Two Pointers: Array partitioning, merging sorted arrays.
    - Sliding Window: Contiguous subarray/substring constraints (e.g., longest substring without repeating characters).
    - Linked List: Fast/slow pointers for midpoints or cycle detection (Floyd's Cycle).
    - Graph: Finding shortest paths in weighted graphs.
#### 4. Dynamic Programming (DP)
- Interview Power Phrase:"By caching sub-problem results and defining a clear state transition, we avoid redundant calculations in overlapping structures."
- Core Algorithms:
    - Kadane’s Algorithm: Tracks the max subarray sum ending at the current position, deciding whether to "restart" the sum.
    - Knapsack: Stores optimal choices in a multi-dimensional table to find max value under constraints.
    - LCS / Edit Distance: Uses matching results of sub-sequences to derive the similarity of the whole sequence.
- Key Scenarios: Maximum subarray, stock trading strategies, path counting, fuzzy string matching.
- Application Logic:
    - Array: Maximum subarray sum, Best Time to Buy and Sell Stock (State Machine DP).
    - Matrix: Shortest path sum in a matrix, Largest Square.
    - String: Longest Common Subsequence (LCS), Word Break, Regular Expression Matching.
#### 5. Exhaustive Search & Backtracking
- Interview Power Phrase:"I'll use a DFS to systematically explore the state space, pruning branches that no longer satisfy the feasibility criteria."
- Core Algorithms:
    - DFS/Backtracking: Tries every possibility and "undoes" steps when hitting a dead end to return to the previous state.
    - BFS: Expands layer-by-layer using a queue to guarantee the shortest path in unweighted graphs.
    - Topological Sort: Uses in-degrees to handle Directed Acyclic Graphs (DAGs) to determine a valid execution sequence.
- Key Scenarios: Island counting, permutations/combinations, task dependencies, puzzles (Sudoku).
- Application Logic:
    - Graph: Cycle detection, finding all paths, number of islands (DFS/BFS).
    - Topological Sort: Scenarios with dependencies (Course Schedule, build systems like Webpack).
    - Tree: Finding all paths from root to leaves.
    - Combinatorics: Letter combinations of a phone number, N-Queens, Sudoku.
#### 💡 Interview Tip: The "Three-Step Pitch"
In a Google L6 interview, try this structured delivery:
1. Observation: "I noticed the problem involves a maximum sum of a contiguous subarray."
2. Strategy: "This suggests Kadane’s Algorithm, which is a highly efficient Linear DP approach."
3. The Pitch: "I'll define the state as the max sum ending at index $i$, allowing us to cache sub-problem results and achieve $O(n)$ time."