package com.qby.jvm.stack;

public class DynamicLinkTest {
    int num = 10;
    public void methodA() {
        System.out.println("methodA");
    }

    public void methodB() {
        System.out.println("methodB");
        methodA();
        num++;
    }

}
