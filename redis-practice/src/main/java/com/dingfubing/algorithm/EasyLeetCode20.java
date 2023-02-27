package com.dingfubing.algorithm;

import java.util.Stack;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/23 15:40
 */
public class EasyLeetCode20 {
    public boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            }  else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }

        }

        return stack.isEmpty();

    }
}
