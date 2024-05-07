package com.lara.arrayandlinkedlist;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author zhanghaibing
 * @date 2024-03-08
 */

public class PalindromeNumberTest {

    private PalindromeNumber util = new PalindromeNumber();

    @Test
    public void testIsPalindromeNumber() {
        // 测试用例1：空或零
        assertTrue(util.isPalindromeNumber(0));
        assertTrue(util.isPalindromeNumber(121));

        // 测试用例2：负数不是回文数
        assertFalse(util.isPalindromeNumber(-121));

        // 测试用例3：正整数回文数
        assertTrue(util.isPalindromeNumber(12321));
        assertTrue(util.isPalindromeNumber(1001));

        // 测试用例4：非回文正整数
        assertFalse(util.isPalindromeNumber(12345));

        // 测试用例5：边界值测试（最大和最小回文数）
        assertTrue(util.isPalindromeNumber(Integer.MAX_VALUE));
        assertFalse(util.isPalindromeNumber(Integer.MIN_VALUE));

        // 测试用例6：包含0的回文数
        assertTrue(util.isPalindromeNumber(10001));
        assertFalse(util.isPalindromeNumber(12021));

        // 测试用例7：多位数且中间有0的回文数
        assertTrue(util.isPalindromeNumber(10000001));
        assertFalse(util.isPalindromeNumber(12000021));
    }
}

class NumberUtil {
    // 假设我们有一个名为isPalindromeNumber的方法实现
    public boolean isPalindromeNumber(int x) {
        // 这里应填写实际的回文数字检测逻辑
        // 由于这是单元测试代码示例，所以省略了具体实现
        return false; // 真实情况中需要替换为正确的实现逻辑
    }
}

