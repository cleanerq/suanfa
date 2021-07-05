package com.qby.redis2.distribute;

public interface RedisBizCall {
    /**
     * 业务回调方法
     *
     * @return 序列化后数据值
     */
    String call();
}
