package com.lara.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *
 * @author zhanghaibing
 * @date 2026-01-30
 */
public class CodeTest {



    @Test
    public void test() {
        String s = "cba";
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        System.out.println(String.valueOf(chars));
    }
}
