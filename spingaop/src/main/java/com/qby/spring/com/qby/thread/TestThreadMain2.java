package com.qby.spring.com.qby.thread;

public class TestThreadMain2 {
    public static void main(String[] args) {
        CustomThreadPool.threadPoolExecutor.execute(()->{
            Thread.currentThread().setName("xxxx12111");
            System.out.println(Thread.currentThread().getName() + "xxx");
        });
    }
}
