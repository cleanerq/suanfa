package com.qby.jdk8.lamda;

import java.util.stream.Stream;

/**
 * 及早求值
 * 在上述例子中用到的 count 、 min 和 max 方 法，因为常用而被纳入标准库中。
 * 事实上，这些方法都是 reduce 操作。及早求值。
 */
public class TestCaseStreamReduce {
    public static void main(String[] args) {
        // 求最小
        System.out.println(Stream.of(1, 2, 3, 4).reduce(1, (acc, x) -> acc < x ? acc : x));
        // 求最大
        System.out.println(Stream.of(1, 2, 3, 4).reduce(1, (acc, x) -> acc > x ? acc : x));
        // 求个数
        System.out.println(Stream.of(1, 2, 3, 2).reduce(0, (acc, x) -> ++acc));
        // 累加
        System.out.println(Stream.of(1, 2, 3, 4).reduce(0, (acc, x) -> acc + x));
    }
}
