package com.zhang.cache;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @program: utilsdemo
 * @description: 测试使用缓存
 * @author: zhangyu
 * @create: 2019-08-07 15:19
 */
public class TestCache2 {
    Logger logger = Logger.getLogger("cacheLog");

    /**
     * 测试缓存和缓存失效
     */
    @Test
    public void testCacheManager() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        logger.info("myTest:" + cacheManagerImpl.getCacheByKey("myTest").getDatas());
    }

    /**
     * 测试缓存和缓存失效
     */
    @Test
    public void testCacheManager2() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        if (cacheManagerImpl.getCacheByKey("test") != null) {
            logger.info("test:" + cacheManagerImpl.getCacheByKey("test").getDatas());
        } else {
            logger.info("test:" + "缓存已经被清空");
        }
        //logger.info("myTest:" + cacheManagerImpl.getCacheByKey("myTest").getDatas());
    }

    /**
     * 测试线程安全
     */
    @Test
    public void testThredSafe() {
        final String key = "thread";
        final CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exec.execute(new Runnable() {
                public void run() {
                    if (!cacheManagerImpl.isContains(key)) {
                        cacheManagerImpl.putCache(key, 1, 0);
                    } else {
                        // 因为+1和赋值操作不是原子性的，所以把它用synchronize块包起来
                        synchronized (cacheManagerImpl) {
                            int value = (Integer) cacheManagerImpl.getCacheDataByKey(key) + 1;
                            cacheManagerImpl.putCache(key, value, 0);
                        }
                    }
                }
            });
        }
        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        logger.info(cacheManagerImpl.getCacheDataByKey(key).toString());
    }
}