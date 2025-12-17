package com.lara.breadthfirstsearch;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode102:
 * Give the root of the binary tree, return the level order traversal of its
 * nodes's values.(i.e., from left to right, level by level)
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * <p>
 * 3
 * 9        20
 * null  null   15  7
 * <p>
 * bfs
 * list<list></>
 *
 * Questions:
 * Core Requirements & Output:
 * Output Format: "The expected output is a List<List<Integer>>. Can you confirm that each inner list should represent one level of the tree?"
 * Order Within Levels: "Within each level (each inner list), should the nodes be ordered from left to right?" (The standard definition implies yes, but confirming is good).
 * Order of Levels: "Should the outer list contain the levels in order from the root (level 0) downwards?" (Again, standard, but confirm).
 *
 * Edge Cases:
 * Empty Tree: "What should be returned if the input root is null? An empty list ([])?" (This is the most common expectation).
 * Single Node Tree: "If the tree has only a root node, should the output be [[root.val]]?"
 *
 * Constraints & Assumptions:
 * Node Values: "Are there any constraints on the range of integer values within the nodes? (e.g., positive, negative, within standard int limits)?" (Usually doesn't affect the algorithm logic but good to know).
 * Tree Size: "Are there any potential constraints on the maximum number of nodes or the maximum depth of the tree? Would extreme sizes require optimizing for memory or speed beyond a standard BFS?" (Usually not critical for typical interviews unless specified).
 * TreeNode Definition: "Can I assume the standard TreeNode definition with val, left, and right attributes?" (LeetCode provides this, but in a real interview, you might confirm).
 *
 *
 * Time Complexity:
 *
 * @author zhanghaibing
 * @date 2025-04-10
 */
public class BinaryTreeLevelOrderTraversalSolution {

    public List<List<Integer>> binaryTreeLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();

        // Edge check case.
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList();
            // Get the number of nodes at the current level.
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
