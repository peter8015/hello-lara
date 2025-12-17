package com.lara.strings;

import org.junit.Test;

/**
 * 判断一个数是否是素数
 * 素数(质数)：
 * 1. 大于1的自然数
 * 2. 只能被1或它自身整除，不能被其他数整除。否则为合数。（1和它自身又被称为正因数）
 *
 * time: 2:15
 *
 * [deliberate practice]
 * @author zhanghaibing
 * @date 2024-06-13
 */
public class Prime {
    @Test
    public void test() {
        System.out.println(isPrime(11));
    }

    /**
     * 判断一个整数是否为质数（素数）。
     * 质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的数。
     * 本方法通过遍历从2到该数平方根的范围，检查是否有能整除该数的因子，从而判断该数是否为质数。
     * <p>
     * 一个数如果可以被小于平方根的数整除，那么它一定可以被大于平方根的数整除。
     * 时间复杂度为：O(sqrt(n)), 其中n为需要检查的整数。
     *
     * @param n 待判断的整数
     * @return 如果n是质数，返回true；否则返回false
     */
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        // 一个数如果可以被小于平方根的数整除，那么它一定可以被大于平方根的数整除。
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test2() {
//        isPrime2(2);
    }

}






