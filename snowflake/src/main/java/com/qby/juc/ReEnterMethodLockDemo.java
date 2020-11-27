package com.qby.juc;

/**
 * 可重入锁:可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * <p>
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 */

public class ReEnterMethodLockDemo {

    public synchronized void m1() {
        System.out.println("=====外层");
        m2();
    }

    public synchronized void m2() {
        System.out.println("=====中层");
        m3();
    }

    public synchronized void m3() {
        System.out.println("=====内层");
    }


    public static void main(String[] args) {
        new ReEnterLockDemo().m1();
    }
}