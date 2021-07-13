package com.qby.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 普通对象
 *
 * 对象头：markword 8  (锁的标识位：标识对象的状态，GC标记：对象被回收了多少次 分代年龄)
 * ClassPointer指针：-XX:+UseCompressedClassPointers 为4字节(默认开启) 不开启为8字节 (对象属于哪个Class)
 * 实例数据 引用类型：-XX:+UseCompressedOops 为4字节(默认开启) 不开启为8字节 Oops Ordinary Object Pointers(成员变量的引用 比如下面的Object o)
 * Padding对齐，8的倍数 (64位的机器 按块来读，一下子读16个字节)
 *
 * 观察虚拟机配置命令 java -XX:+PrintCommandLineFlags -version
 *
 */
public class HelloObjSz {
    public static void main(String[] args) {
        printIntSz();

    }

    public static void printIntSz() {
        // 二维数组 占用字节 为 第一维个数 * 类型占用字节数(int=4)
        int[][] sz1 = new int[10][50];
        int[][] sz2 = new int[50][10];

        String s1 = ClassLayout.parseInstance(sz1).toPrintable();
        String s2 = ClassLayout.parseInstance(sz2).toPrintable();

        System.out.println(s1);
        System.out.println(s2);
    }

    public static void printStringSz() {
        // 二维数组 占用字节 为 第一维个数 * 类型占用字节数(int=4)
        String[][] sz1 = new String[10][50];
        String[][] sz2 = new String[50][10];

        String s1 = ClassLayout.parseInstance(sz1).toPrintable();
        String s2 = ClassLayout.parseInstance(sz2).toPrintable();

        System.out.println(s1);
        System.out.println(s2);
    }
}
