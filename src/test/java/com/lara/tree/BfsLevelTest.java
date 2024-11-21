package com.lara.tree;

import org.junit.jupiter.api.Test;
import utils.TreeNode;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BfsLevelTest{

    @Test
    public void testLevelOrder2FullBinaryTree() {
        BfsLevel bfsLevel = new BfsLevel();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
    
        List<List<Integer>> result = bfsLevel.levelOrder2(root);
        assertEquals(3, result.size(), "The result should have three levels");
        assertEquals(1, result.get(0).get(0), "The root value should be 1");
        assertEquals(2, result.get(1).get(0), "The left child value should be 2");
        assertEquals(3, result.get(1).get(1), "The right child value should be 3");
        assertEquals(4, result.get(2).get(0), "The left grandchild value should be 4");
        assertEquals(5, result.get(2).get(1), "The right grandchild value should be 5");
        assertEquals(6, result.get(2).get(2), "The left grandchild value should be 6");
        assertEquals(7, result.get(2).get(3), "The right grandchild value should be 7");
    }

    @Test
    public void testLevelOrder2EmptyTree() {
        BfsLevel bfsLevel = new BfsLevel();
        List<List<Integer>> result = bfsLevel.levelOrder2(null);
        assertEquals(0, result.size(), "The result should be an empty list for an empty tree");
    }

    @Test
    public void testLevelOrder2SingleNode() {
        BfsLevel bfsLevel = new BfsLevel();
        TreeNode root = new TreeNode(1);
        List<List<Integer>> result = bfsLevel.levelOrder2(root);
        assertEquals(1, result.size(), "The result should have one level");
        assertEquals(1, result.get(0).get(0), "The root value should be 1");
    }

    @Test
    public void testLevelOrder2MultipleLevels() {
        BfsLevel bfsLevel = new BfsLevel();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
    
        List<List<Integer>> result = bfsLevel.levelOrder2(root);
        assertEquals(3, result.size(), "The result should have three levels");
        assertEquals(1, result.get(0).get(0), "The root value should be 1");
        assertEquals(2, result.get(1).get(0), "The left child value should be 2");
        assertEquals(2, result.get(1).size(), "The left level should have two nodes");
        assertEquals(4, result.get(2).get(0), "The right child value should be 3");
        assertEquals(2, result.get(2).size(), "The right level should have one node");
    }

}