package com.qby.juc;

import java.util.concurrent.atomic.AtomicInteger;

class Data {
    // 未加锁
//    volatile int num = 0;
    // 无锁 CAS
    volatile AtomicInteger num = new AtomicInteger();

    public void increase() {
//        num++;
        num.incrementAndGet();
        System.out.println("当前线程ID:" + Thread.currentThread().getId() + " 当前结果:" + num);
    }
}

public class VolatileTest {
    public static void main(String[] args) {
        Data data = new Data();

        // 100个线程操作num累加
        for (int i = 1; i <= 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                        data.increase();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }).start();
        }

        // 等待上述线程执行完 -> 数值2表示只有主线程和GC线程在运行
        while (Thread.activeCount() > 2) {
            // 主线程让出CPU时间片
            Thread.yield();
        }
        System.out.println(data.num);
    }
}
