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
public class HelloObj {
    public static void main(String[] args) {
        Qby t = new Qby();
        String s = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(s);

        t.hashCode();
        s = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(s);

        synchronized (t) {
            s = ClassLayout.parseInstance(t).toPrintable();
            System.out.println(s);
        }

        s = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(s);

    }

    static class Qby {
        boolean b = true;
        String a = "d";
        int c = 3;
        int d = 3;
    }
}
