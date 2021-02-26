package com.qby.juc;

public class LockSupportDemoSync {
    private static Object objectLock = new Object();

    private static void synchronizedWaitNotify() {
        new Thread(() -> {
//            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "------被唤醒");
//            }
        }, "A").start();

        new Thread(() -> {
//            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "------通知");
//            }
        }, "B").start();
    }

    public static void main(String[] args) {
        synchronizedWaitNotify();
    }
}
