package com.qby.suanfa;

import java.util.Stack;

public class StackKuohao {
    public boolean isValid(String s) {
        if (s.isEmpty())
            return true;
        Stack stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.empty() || c != stack.pop())
                return false;
        }
        if (stack.empty())
            return true;
        return false;
    }
}
