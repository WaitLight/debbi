package org.dl.debbi.common.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.dl.debbi.common.error.domain.ErrorLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonCache {

    // 异常信息缓存
    @Bean("errorLogCache")
    public Cache<String, ErrorLog> errorLogCache() {
        return CacheBuilder.newBuilder().maximumSize(20).build();
    }
}
