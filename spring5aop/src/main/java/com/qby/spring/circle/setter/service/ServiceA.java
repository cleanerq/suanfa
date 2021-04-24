package com.qby.spring.circle.setter.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceA {

    private ServiceB serviceB;

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
        System.out.println("A 里面设置了B");
    }
}

