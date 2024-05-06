package cn.mila.book_test_master.core.config;

import cn.mila.book_test_master.core.constant.CacheConsts;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 缓存配置类
 *
 * @author mila
 * @date 2024/5/5 下午9:55
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Bean
    public CacheManager caffeineCacheManager() {
        log.info("加载caffeineCacheManager...");
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<CaffeineCache>(CacheConsts.CacheEnum.values().length);
        for (var c : CacheConsts.CacheEnum.values()) {
            // 设置最大保存数量
            Caffeine<Object, Object> caffeine = Caffeine.newBuilder().maximumSize(c.getMaxSize());
            if (c.getTtl() > 0) {
                // 设置过期时间
                caffeine.expireAfterWrite(Duration.ofSeconds(c.getTtl()));
            }
            caches.add(new CaffeineCache(c.getName(), caffeine.build()));
        }

        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
