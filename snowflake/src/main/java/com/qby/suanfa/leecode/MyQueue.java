package com.qby.suanfa.leecode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 将一个栈当作输入栈，用于压入 \texttt{push}push 传入的数据；另一个栈当作输出栈，
 * 用于 \texttt{pop}pop 和 \texttt{peek}peek 操作。
 * <p>
 * 每次 \texttt{pop}pop 或 \texttt{peek}peek 时，若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈，
 * 这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode-s-xnb6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public MyQueue() {
        inStack = new LinkedList<Integer>();
        outStack = new LinkedList<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
