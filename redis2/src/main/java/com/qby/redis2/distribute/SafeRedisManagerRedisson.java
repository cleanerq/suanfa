package com.qby.redis2.distribute;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 安全缓存管理器
 *
 * @author 微信公众号「JAVA前线」
 */
@Slf4j
@Service
public class SafeRedisManagerRedisson {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private Redisson redisson;

    public String getDataSafe(String key, int lockExpireSeconds,
                              int dataExpireSeconds, RedisBizCall bizCall,
                              boolean alwaysRetry) {
        boolean getLockSuccess = false;
        /** 竞争分布式锁 **/
        RLock lock = redisson.getLock(key);
        try {
            while (true) {
                String value = (String) redisTemplate.opsForValue().get(key);
                if (StringUtils.isNotEmpty(value)) {
                    return value;
                }

                if (getLockSuccess = lock.tryLock(lockExpireSeconds, TimeUnit.SECONDS)) {
                    value = (String) redisTemplate.opsForValue().get(key);
                    if (StringUtils.isNotEmpty(value)) {
                        return value;
                    }
                    /** 查询数据库 **/
                    value = bizCall.call();

                    /** 数据库无数据则返回**/
                    if (StringUtils.isEmpty(value)) {
                        return null;
                    }

                    /** 数据存入缓存 **/
                    redisTemplate.opsForValue().set(key, value, dataExpireSeconds);
                    return value;
                } else {
                    if (!alwaysRetry) {
                        log.warn("竞争分布式锁失败,key={}", key);
                        return null;
                    }
                    Thread.sleep(100L);
                    log.warn("尝试重新获取数据,key={}", key);
                }
            }
        } catch (Exception ex) {
            log.error("getDistributeSafeError", ex);
            return null;
        } finally {
            //还在持有锁的状态，并且是当前线程持有的锁再解锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
