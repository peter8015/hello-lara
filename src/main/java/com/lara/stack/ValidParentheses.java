package com.lara.stack;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertTrue;

/**
 * 题目描述
 * 题目20："Valid Parentheses" (有效的括号)，给定一个只包括 '('，')'，'{'，'}'，'[' 和 ']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：左括号必须用相同类型的右括号闭合。左括号必须以正确的顺序闭合。注意空字符串可被认为是有效字符串。
 *
 * 解题思路
 * 要解决这个问题，我们可以使用栈来匹配括号。具体思路如下：
 *
 * 1. 创建一个空栈，用来存储左括号。
 * 2. 遍历给定的字符串，对于每个字符：
 * 3. 如果是左括号（'('，'{'，'['），则将其推入栈中。
 * 4. 如果是右括号，则检查栈顶元素与当前字符是否匹配，若匹配则将栈顶元素出栈，继续遍历下一个字符；若不匹配，则返回false。
 * 5. 若遍历结束后栈为空，则表示括号匹配完全，返回true；否则返回false。
 *
 * 时间复杂度分析
 * 假设字符串的长度为n。
 * 遍历字符串的时间复杂度为O(n)。
 * 在每次遍历中，栈的操作包括压栈和弹栈，都是O(1)的时间复杂度。 因此，整体时间复杂度为O(n)。
 *
 * leetcode 20: Valid Parentheses
 * Given a string s containg just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * Example:
 * Input: "()"
 * Output: true
 *
 * Solution Approach
 * To solve this problem, we can use a stack to match parentheses. The specific approach is as follows:
 *
 * 1. Create an empty stack to store left parentheses.
 * 2. Traverse the given string, and for each character:
 * 3. If it is a left parenthesis ('(', '{', '['), then push it onto the stack.
 * 4. If it is a right parenthesis, check if it matches the top element of the stack. If there is a match, pop the top element from the stack and continue to the next character; if there is no match, return false.
 * 5. After the traversal, if the stack is empty, then the parentheses are completely matched, so return true; otherwise, return false.
 *
 * @author zhanghaibing
 * @date 2024-04-25
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        if(s == null || s.length() == 0) {
            return true;
        }
        // "([{}])"
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                char k = c == '(' ? ')' : c == '[' ? ']' : '}';
                stack.push(k);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                if(c != last) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public boolean isValid2(String s) {
        int len = s.length();
        if(s == null || len == 0) {
            return true;
        }

        Stack<Character> stack = new Stack();
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if(c == '(' || c == '[' || c == '{') {
                char cc = c == '(' ? ')' : c == '[' ? ']' : c == '{' ? '}' : c;
                stack.push(cc);
            } else {
                if(stack.isEmpty()) {
                    return true;
                }

                char last = stack.pop();
                if(c != last) {
                    return false;
                }

            }
        }
        return stack.isEmpty();
    }


    @Test
    public void test() {
        String s = "([{}])";

        boolean isValid = isValid2(s);

        assertTrue(isValid);

    }


}
