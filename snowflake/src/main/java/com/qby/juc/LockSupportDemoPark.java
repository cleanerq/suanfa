package com.qby.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * locksupport park unpark
 */
public class LockSupportDemoPark {
    public static void main(String[] args) {
        /**
         LockSupport：俗称 锁中断
         以前的两种方式：
         1.以前的等待唤醒通知机制必须synchronized里面有一个wait和notify
         2.lock里面有await和signal
         这上面这两个都必须要持有锁才能干，
         LockSupport它的解决的痛点
         1。LockSupport不用持有锁块，不用加锁，程序性能好，
         2。先后顺序，不容易导致卡死
         */
        Thread t1 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();//阻塞当前线程
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();



        Thread t2 = new Thread(() -> {
            LockSupport.unpark(t1);//唤醒t1线程
            System.out.println(Thread.currentThread().getName() + "\t 通知t1...");
        }, "t2");
        t2.start();
    }
}
