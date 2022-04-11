package com.github.tangyi.gateway.cache.loader;

import com.github.tangyi.common.basic.cache.loadingcache.LoadingCacheHelper;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * 加载演示环境配置
 *
 * @author zdz
 * @date 2022/04/11 21:52
 */
@Slf4j
public class PreviewConfigLoader extends CacheLoader<String, Map<String, String>> {

    /**
     * 30秒刷新一次loadingCache
     */
    public static final int REFRESH_CACHE_DURATION = 30;

    /**
     * 是否启动preview演示
     */
    public static final String PREVIEW_ENABLE = "preview_enable";

    /**
     * 加载配置信息
     * @param key Key
     * @return 配置信息
     */
    @Override
    public Map<String, String> load(String key) throws Exception {
        return loadData(key);
    }

    @Override
    public ListenableFuture<Map<String, String>> reload(String key, Map<String, String> oldValue) throws Exception {
        return LoadingCacheHelper.REFRESH_POOLS.submit(() -> loadData(key));
    }

    /**
     * 从redis中获取配置信息
     *
     * @param key Key
     * @return 配置信息
     */
    @SuppressWarnings("unchecked")
    private static Map<String, String> loadData(String key) {
        // 从Redis获取配置
        RedisTemplate<String, String> redisTemplate =
                (RedisTemplate) SpringContextHolder.getApplicationContext().getBean("redisTemplate");
        Object enablePreview = redisTemplate.opsForValue().get(PREVIEW_ENABLE);
        if (enablePreview != null) {
            return Collections.singletonMap(PREVIEW_ENABLE, enablePreview.toString());
        }
        return Collections.emptyMap();
     }

}
