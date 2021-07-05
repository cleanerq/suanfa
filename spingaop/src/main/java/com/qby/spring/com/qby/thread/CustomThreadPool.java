package com.qby.spring.com.qby.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(), 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), r -> {
        Thread thread = new Thread(r, "自定义线程池");
        return thread;
    }, new ThreadPoolExecutor.DiscardOldestPolicy());


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(), 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), r -> {
            Thread thread = new Thread(r, "自定义线程池");
            return thread;
        }, new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
