package com.qby.spring.com.qby.thread;

public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        test03();
    }

    public static void test01() throws InterruptedException {
        ThreadTest t1 = new ThreadTest("A");
        ThreadTest t2 = new ThreadTest("B");
        t1.start();
        t1.join();
        t2.start();
    }

    public static void test02() {
        // TODO Auto-generated method stub
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadTest t1 = new ThreadTest("A");
        ThreadTest t2 = new ThreadTest("B");
        ThreadTest t3 = new ThreadTest("C");
        System.out.println("t1start");
        t1.start();
        System.out.println("t2start");
        t2.start();
        System.out.println("t3start");
        t3.start();
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void test03() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadTest t1 = new ThreadTest("A");
        ThreadTest t2 = new ThreadTest("B");
        ThreadTest t3 = new ThreadTest("C");
        System.out.println("t1start");
        t1.start();
        System.out.println("t1end");
        System.out.println("t2start");
        t2.start();
        System.out.println("t2end");
        t1.join();
        System.out.println("t3start");
        t3.start();
        System.out.println("t3end");
        System.out.println(Thread.currentThread().getName() + " end");
    }
}

class ThreadTest extends Thread {

    public ThreadTest(String name) {
        super(name);
    }

    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + "-" + i);
        }
    }
}
