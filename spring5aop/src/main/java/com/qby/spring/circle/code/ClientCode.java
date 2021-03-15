package com.qby.spring.circle.code;

public class ClientCode {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.setB(b);
        b.setA(a);
    }
}
