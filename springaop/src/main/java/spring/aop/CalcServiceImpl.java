package spring.aop;

import org.springframework.stereotype.Service;

/**
 * @author qby
 * @date 2021/3/9 23:36
 */
@Service
public class CalcServiceImpl implements CalcService {

    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("=========>CalcServiceImpl被调用了,我们的计算结果：" + result);
        return result;

    }
}
