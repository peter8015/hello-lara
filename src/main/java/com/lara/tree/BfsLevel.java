package com.lara.tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhanghaibing
 * @date 2024-02-02
 */
public class BfsLevel {
    /**
     * leetcode 102 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     *
     * 解题思路：利用队列存储二叉树节点。
     *
     * 时间复杂度：
     * 每个点进队出队各一次，故渐进时间复杂度为 O(n)。
     *
     * 易错点：
     * 1. 写循环时不要用queue.size()，会发生变化，用变量代替。
     * 2. 用offer，最好不用add，队列满时add会报错。
     */
    public List<List<Integer>> levelOrderx(TreeNode root) {
        List<List<Integer>> result = new ArrayList();

        // 1. 如果root为空返回。
        if(root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        // 2. 循环队列，直到为空
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> r = new ArrayList();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> r = new ArrayList();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                ;;
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                r.add(node.val);
            }
            result.add(r);
        }
        return result;
    }


    public List<List<Integer>> levelOrdery(TreeNode root) {
        List<List<Integer>> result = new ArrayList();

        if(root == null) {
            return null;
        }

        return null;
    }

}

