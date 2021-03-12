package com.qby.spring;


import com.qby.spring.aop.CalcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest  //spring5
public class CalcServiceImplTest {
    @Autowired
    private CalcService service;

    @Test
    public void testAop4() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "SpringBoot版本：" + SpringBootVersion.getVersion());

        System.out.println();

        service.div(10, 2);
    }

}

