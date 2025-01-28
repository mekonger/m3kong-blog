package com.m3kong.infrastructure.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
@AllArgsConstructor
public class CaffeineCacheImpl<T> implements CaffeineCache<T> {

  final private Cache<String, T> caffeineCache;

  @Override
  public void setObject(String key, T value) {
    if (!StringUtils.hasLength(key)) { // null or ''
      return;
    }

    caffeineCache.put(key, value);
  }

  @Override
  public T getObject(String key) {

    T result = caffeineCache.getIfPresent(key);
    return result;
  }

}
