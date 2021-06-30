package com.qby.suanfa.sort;

import java.util.Stack;

public class StackSort {
    public static void main(String[] args) {
        Stack<Integer> input = new Stack<>();
        int length = 20;
        for (int i = 0; i < length; i++) {
//            System.out.println(Math.random());
            int tInt = (int) (Math.random() * 1000);
            input.push(tInt);
        }
        System.out.println(input);


        System.out.println(stackSort(input));

    }

    /**
     * 利用栈排序
     *
     * @param input
     * @return
     */
    public static Stack<Integer> stackSort(Stack<Integer> input) {
        Stack<Integer> res = new Stack<>();

        while (!input.isEmpty()) {
            Integer pop = input.pop();
            while (!res.isEmpty() && res.peek() > pop) {
                input.push(res.pop());
            }
            res.push(pop);
        }

        return res;
    }
}
