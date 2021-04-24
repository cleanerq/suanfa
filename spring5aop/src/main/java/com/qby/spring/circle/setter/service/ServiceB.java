package com.qby.spring.circle.setter.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceB {

    private ServiceA serviceA;

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
        System.out.println("B 里面设置了A");
    }
}

