package com.qby.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition接口中的await后signal方法实现线程的等待和唤醒
 * 小总结：
 * await和signal方法必须要在lock和unlock里面使用
 * 先await后signal才OK，否则会一直等待
 */
public class LockSupportDemoAwait {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();


    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "------被唤醒");
            } finally {
                lock.unlock();
            }
        }, "A").start();
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "------通知");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }
}
