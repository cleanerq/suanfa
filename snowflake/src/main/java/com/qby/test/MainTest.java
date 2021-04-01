package com.qby.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {
    public static void main(String[] args) {
//        Snowflake snowflake = IdUtil.getSnowflake(0, 0);
//
//        int a = Integer.MAX_VALUE;
//        a = Integer.MIN_VALUE;
//
//        AtomicInteger ai = new AtomicInteger(1);
//
//        ai.compareAndSet(1, 2);
//
//        ai.compareAndSet(2, 3);
//
//        ai.compareAndSet(3, 1);

        String a = "111";
        String b = new String("111");
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);

    }
}
