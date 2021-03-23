package com.qby.redis2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtils {
    @Value("${spring.redis.port}")
    private int serverPort;

    @Value("${spring.redis.host}")
    private String serverHost;

    private static JedisPool jedisPool;


    public JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (JedisPool.class) {
                if (jedisPool == null) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(20);
                    jedisPoolConfig.setMaxIdle(10);
                    jedisPool = new JedisPool(jedisPoolConfig, getServerHost(), getServerPort(), 100000);
                }
            }
        }
        return jedisPool;
    }


    public Jedis getJedis() throws Exception {
        if (jedisPool == null) {
            getJedisPool();
        }

        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        throw new Exception("Jedispool is not ok");
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }
}