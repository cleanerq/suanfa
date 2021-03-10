package com.qby.spring.aop;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CalcServiceImplTest {

    @Autowired
    private CalcService calcService;

    /**
     * spring4版本aop
     */
    @Test
    public void testAop4() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "springboot版本：" + SpringBootVersion.getVersion());
        System.out.println();
        calcService.div(4, 2);
    }
}
