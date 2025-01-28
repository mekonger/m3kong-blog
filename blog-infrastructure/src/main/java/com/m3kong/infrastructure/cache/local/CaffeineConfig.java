package com.m3kong.infrastructure.cache.local;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.m3kong.infrastructure.utils.ObjectUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {
  @Bean
  public com.github.benmanes.caffeine.cache.Cache<String, Object> caffeineCache() {
    return Caffeine.newBuilder()
            .maximumWeight(50 * 1024 * 1024) // Giới hạn 50 MB
            .weigher((String key, Object value) -> ObjectUtil.calculateObjectSizeInBytes(value)) // Tính trọng lượng theo byte
            .expireAfterWrite(1, TimeUnit.DAYS) // Entry hết hạn sau 1 ngày
            .build();
  }
}
