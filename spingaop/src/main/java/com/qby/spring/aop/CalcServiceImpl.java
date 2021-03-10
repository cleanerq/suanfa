package com.qby.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {

    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("=========>CalcServiceImpl被调用了,我们的计算结果：" + result);
        return result;
    }
}


