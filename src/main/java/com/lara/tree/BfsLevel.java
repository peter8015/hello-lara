package com.lara.tree;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Statement: LeetCode 102 Binary Tree Level Traversal
 * Given the root of a binary tree, return the level order traversal of its node values (i.e., from left to right, level by level).
 *
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Problem-solving Approach:
 * 1. Use a queue(FIFO) to store the nodes of the binary tree.
 * 2. Start by adding the root node to the queue.
 * 3. Process nodes level by level.
 *
 * Time Complex:
 * - Each node is added to the queue and removed exactly once.
 * - Total operations are proportional to the number of nodes (n).
 * - Thus, the time complexity is O(n).
 *
 * @author zhanghaibing
 * @date 2024-02-02
 */
public class BfsLevel {
//    private static final Logger log = LoggerFactory.getLogger(BfsLevel.class);

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        // Use a queue to store the nodes of the binary tree.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> r = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                r.add(node.val);

                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(r);
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
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


    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList();

        // edge case check
        if(root == null) return result;

        // use queue to store the nodes of the binary tree
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        // process nodes level by level
        while(!queue.isEmpty()) {
            List<Integer> level = new ArrayList();
            int size = queue.size(); //*

            for(int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                level.add(node.val);  //*

                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> result = new ArrayList();

        // edge case check
        if(root == null) return result;

        // use queue to store nodes
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        // process nodes level by level
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            result.add(level);
        }
        return result;
    }

}






















