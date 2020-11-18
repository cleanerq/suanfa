package com.qby.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class MainTest {
    public static void main(String[] args) {
        Snowflake snowflake = IdUtil.getSnowflake(0, 0);

    }
}
