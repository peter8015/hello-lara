package com.lara.tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TreeNode;

/**
 * leetcode 2415 反转二叉树
 *  给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
 * 反转后，返回树的根节点。
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 * 节点的 层数 等于该节点到根节点之间的边数。
 *
 * 输入：root = [2,3,5,8,13,21,34]
 * 输出：[2,5,3,8,13,21,34]
 * 解释：
 * 这棵树只有一个奇数层。
 * 在第 1 层的节点分别是 3、5 ，反转后为 5、3 。
 *
 * @author zhanghaibing
 * @date 2024-09-28
 */
public class ReverseBinaryTree {
    private static Logger log = LoggerFactory.getLogger(ReverseBinaryTree.class);

    /**
     * 算法思路：
     *  1. 递归遍历：使用深度优先遍历（DFS）来遍历每一层的节点。
     *  2. 反转节点：在奇数层记录节点的层级，并对奇数层的节点进行左右子节点的交换。
     *  3. 更新节点：将反转后的节点重新赋值给root。
     * 时间复杂度：O(n) n 为二叉树的节点数据。
     * 空间复杂度：O(h) h为二叉树的高度。
     *
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        reverseOddLevelsHelper(root.left, root.right, 1);
        return root;
    }

    private void reverseOddLevelsHelper(TreeNode left, TreeNode right, int level) {
        if (left == null && right == null) return;

        if(level % 2 == 1) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }
        reverseOddLevelsHelper(left.left, left.right, level + 1);
        reverseOddLevelsHelper(left.right, left.left, level + 1);
    }

    @Test
    public void testReverse_withOddDepth() {
        ReverseBinaryTree reverseBinaryTree = new ReverseBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeNode result = reverseBinaryTree.reverseOddLevels(root);

        Assertions.assertEquals(root.left.val, 3, "Expected left child to be 3");
        Assertions.assertEquals(root.right.val, 2, "Expected right child to be 2");
        Assertions.assertEquals(root.left.left.val, 4, "Expected left child of left child to be 4");
        Assertions.assertEquals(root.left.right.val, 5, "Expected right child of left child to be 5");
    }
}
