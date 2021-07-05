package com.qby.redis2.test;

public class Singleton {

    public Object readResolve() {
        Object a = new Object();
        return null;
    }
}
