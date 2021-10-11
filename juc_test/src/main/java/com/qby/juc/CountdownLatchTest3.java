package com.qby.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountdownLatchTest3 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    long threadId = Thread.currentThread().getId();
                    System.out.println(threadId + "准备一起执行");
                    countDownLatch.await();

                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(threadId + "执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.countDown();
        }
    }
}
