package com.qby.redis1.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
public class GoodController {


    public static final String REDIS_LOCK_KEY = "lockhhf";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Redisson redisson;

//    /**
//     * 自己写的
//     *
//     * @return
//     * @throws Exception
//     */
//    @GetMapping("/buy_goods")
//    public String buy_Goods() throws Exception {
//
//        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
//
//        try {
//            //setIfAbsent() == setnx 就是如果不存在就新建，同时加上过期时间保证原子性
//            Boolean lockFlag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value, 10L, TimeUnit.SECONDS);
//            stringRedisTemplate.expire(REDIS_LOCK_KEY, 10L, TimeUnit.SECONDS);
//            if (!lockFlag) {
//                return "抢锁失败，┭┮﹏┭┮";
//            } else {
//                String result = stringRedisTemplate.opsForValue().get("goods:001");
//                int goodsNumber = result == null ? 0 : Integer.parseInt(result);
//
//                if (goodsNumber > 0) {
//                    int realNumber = goodsNumber - 1;
//                    stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
//                    System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort);
//                    return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
//                } else {
//                    System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
//                }
//                return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
//            }
//        } finally {
//            String script = "if redis.call('get', KEYS[1]) == ARGV[1]" + "then "
//                    + "return redis.call('del', KEYS[1])" + "else " + "  return 0 " + "end";
//            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
//            stringRedisTemplate.execute(redisScript, Collections.singletonList(REDIS_LOCK_KEY), value);
//        }
//    }

    /**
     * 使用redission实现
     * 来源于官网 https://redis.io/topics/distlock
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/buy_goods2")
    public String buy_Goods2() throws Exception {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        RLock redissonLock = redisson.getLock(REDIS_LOCK_KEY);
        redissonLock.lock();
        try {
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
        } finally {
            //还在持有锁的状态，并且是当前线程持有的锁再解锁
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }
    }
}

