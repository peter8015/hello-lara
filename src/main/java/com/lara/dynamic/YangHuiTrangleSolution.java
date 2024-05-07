package com.lara.dynamic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode. 118. 杨辉三角 简单
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 时间复杂度：
 * 内外两层循环，O(n^2), n为numRow。
 *
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]s
 *
 * @author zhanghaibing
 * @date 2024-03-19
 */
public class YangHuiTrangleSolution {
    public List<List<Integer>> YangHuiTrangle(int rowNum) {
        List<List<Integer>> result = new ArrayList();

        for(int i = 0; i < rowNum; i++) {
            List<Integer> row = new ArrayList();

            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }


    public static void main(String[] args) {
        YangHuiTrangleSolution solution = new YangHuiTrangleSolution();
        List<List<Integer>> result = solution.YangHuiTrangle(5);
        System.out.println(result);
    }

    @Test
    public void test() {
        YangHuiTrangleSolution solution = new YangHuiTrangleSolution();
        List<List<Integer>> result = solution.YangHuiTrangle(5);
        System.out.println(result);
    }
}
