package com.qby.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)  //1.5.9
public class CalcServiceImplTest {
    @Autowired
    private CalcService service;

    /**
     * 正常
     */
    @Test
    public void test01() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "SpringBoot版本：" + SpringBootVersion.getVersion());

        System.out.println();

        service.div(10, 2);
    }

    /**
     * 异常
     */
    @Test
    public void test02() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "SpringBoot版本：" + SpringBootVersion.getVersion());

        System.out.println();

        service.div(10, 0);
    }

}
