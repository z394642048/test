package com.chaoxing.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RedisLock implements AutoCloseable {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RedisLock.class);
    public static final String REDIS_LOCK = "RedisLock:";


    private static final long DEFAULT_WAIT_LOCK_TIME_OUT = 60;//60s 有慢sql，超时时间设置长一点
    private static final long DEFAULT_EXPIRE = 80;//80s 有慢sql，超时时间设置长一点
    private String key;
    private RedisTemplate redisTemplate;

    public RedisLock(RedisTemplate redisTemplate,String key) {
        this.redisTemplate = redisTemplate;
        this.key = key;
    }

    /**
     * 等待锁的时间,单位为s
     *
     * @param key
     * @param timeout s
     * @param seconds
     */
    public boolean lock(String key, long timeout, TimeUnit seconds) {
        String lockKey = generateLockKey(key);
        long nanoWaitForLock = seconds.toNanos(timeout);
        long start = System.nanoTime();

        try {
            while ((System.nanoTime() - start) < nanoWaitForLock) {
                if (redisTemplate.getConnectionFactory().getConnection().setNX(lockKey.getBytes(), new byte[0])) {
                    //暂设置为80s过期，防止异常中断锁未释放
                    redisTemplate.expire(lockKey, DEFAULT_EXPIRE, TimeUnit.SECONDS);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("add RedisLock[{}].{}", key, Thread.currentThread());
                    }
                    return true;
                }
                //加随机时间防止活锁
                TimeUnit.MILLISECONDS.sleep(1000 + new Random().nextInt(100));
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
            unlock();
        }
        return false;
    }

    public void unlock() {
        try {
            String lockKey = generateLockKey(key);
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            connection.del(lockKey.getBytes());
            connection.del(key.getBytes());
            connection.close();
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }

    private String generateLockKey(String key) {
        return String.format(REDIS_LOCK + "%s", key);
    }

    public boolean lock() {
        return lock(key, DEFAULT_WAIT_LOCK_TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    public void close(){
        try {
            String lockKey = generateLockKey(key);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("release RedisLock[" + lockKey + "].");
            }
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            connection.del(lockKey.getBytes());
            connection.close();
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }
}