package com.qby.redis2.distribute;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 安全缓存管理器
 *
 * @author 微信公众号「JAVA前线」
 *
 */
@Service
public class SafeRedisManager {
//    @Resource
//    private RedisClient RedisClient;
//    @Resource
//    private RedisLockManager redisLockManager;
//
//    public String getDataSafe(String key, int lockExpireSeconds, int dataExpireSeconds, RedisBizCall bizCall, boolean alwaysRetry) {
//        boolean getLockSuccess = false;
//        try {
//            while(true) {
//                String value = redisClient.get(key);
//                if (StringUtils.isNotEmpty(value)) {
//                    return value;
//                }
//                /** 竞争分布式锁 **/
//                if (getLockSuccess = redisLockManager.tryLock(key, lockExpireSeconds)) {
//                    value = redisClient.get(key);
//                    if (StringUtils.isNotEmpty(value)) {
//                        return value;
//                    }
//                    /** 查询数据库 **/
//                    value = bizCall.call();
//
//                    /** 数据库无数据则返回**/
//                    if (StringUtils.isEmpty(value)) {
//                        return null;
//                    }
//
//                    /** 数据存入缓存 **/
//                    redisClient.setex(key, dataExpireSeconds, value);
//                    return value;
//                } else {
//                    if (!alwaysRetry) {
//                        logger.warn("竞争分布式锁失败,key={}", key);
//                        return null;
//                    }
//                    Thread.sleep(100L);
//                    logger.warn("尝试重新获取数据,key={}", key);
//                }
//            }
//        } catch (Exception ex) {
//            logger.error("getDistributeSafeError", ex);
//            return null;
//        } finally {
//            if (getLockSuccess) {
//                redisLockManager.unLock(key);
//            }
//        }
//    }
}
