package com.lara.breadthfirstsearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.TreeNode;

public class BinaryTreeLevelOrderTraversalSolutionTest {

    private BinaryTreeLevelOrderTraversalSolution solution;

    @BeforeEach
    public void setUp() {
        solution = new BinaryTreeLevelOrderTraversalSolution();
    }

    @BeforeEach
    public void setUp1() {
        solution = new BinaryTreeLevelOrderTraversalSolution();
    }

    @Test
    public void binaryTreeLevelOrder_EmptyTree_ReturnsEmptyList() {
        TreeNode root = null;
        List<List<Integer>> result = solution.binaryTreeLevelOrder(root);
        assertTrue(result.isEmpty(), "Expected empty list for empty tree");
    }

    @Test
    public void binaryTreeLevelOrder_SingleNodeTree_ReturnsSingleList() {
        TreeNode root = new TreeNode(1);
        List<List<Integer>> result = solution.binaryTreeLevelOrder(root);
        assertEquals(Arrays.asList(Arrays.asList(1)), result, "Expected single list with root value");
    }

    @Test
    public void binaryTreeLevelOrder_FullBinaryTree_ReturnsCorrectLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6, 7)
        );

        List<List<Integer>> result = solution.binaryTreeLevelOrder(root);
//        List<List<Integer>> result = solution.binaryTreeLevelOrder1(root);
        assertEquals(expected, result, "Expected correct level order for full binary tree");
    }

    @Test
    public void binaryTreeLevelOrder_UnbalancedTree_ReturnsCorrectLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5)
        );

        List<List<Integer>> result = solution.binaryTreeLevelOrder(root);
        assertEquals(expected, result, "Expected correct level order for unbalanced tree");
    }

    @Test
    public void binaryTreeLevelOrder_SingleSideTree_ReturnsCorrectLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4)
        );

        List<List<Integer>> result = solution.binaryTreeLevelOrder(root);
        assertEquals(expected, result, "Expected correct level order for single side tree");
    }
}
