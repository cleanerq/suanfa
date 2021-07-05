package com.qby.suanfa.sort;

import java.util.Stack;

public class StackQueue {
    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public Integer pop() {
        if (popStack.size() <= 0) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public void push(Integer i) {
        pushStack.push(i);
    }

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();

        stackQueue.push(1);
        stackQueue.push(2);
        stackQueue.push(3);

        System.out.println(stackQueue.pop());
        System.out.println(stackQueue.pop());
        stackQueue.push(4);
        System.out.println(stackQueue.pop());
        System.out.println(stackQueue.pop());
        stackQueue.push(5);
        System.out.println(stackQueue.pop());
    }
}
