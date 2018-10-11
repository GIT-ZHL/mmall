package com.haoliang.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhaohaoliang.
 * @Date Created in 下午3:31 2018/10/2
 */
public class TokenCache {

    private static Logger log = LoggerFactory.getLogger(TokenCache.class);

    /**
     * 采用LRU算法维护缓存
     */
    private static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            // 初始化容量
            .initialCapacity(1000)
            // 最大容量
            .maximumSize(100000)
            // 过期时间
            .expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                /**
                 * 默认的数据加载实现。当调用get方法获取值时，如果key没有对应的值将调用该方法进行加载。
                 *
                 * @param key
                 * @return 响应对象
                 * @throws Exception
                 */
                @Override
                public String load(String key) throws Exception {
                    return "null";
                }
            });

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        TokenCache.loadingCache.put(key, value);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        String value = null;
        try {
            value = TokenCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception exception) {
            TokenCache.log.error("localCache get error", exception);
        }
        return value;
    }
}
