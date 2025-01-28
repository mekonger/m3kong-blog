package com.m3kong.infrastructure.cache.local;

public interface CaffeineCache<T> {
  void setObject(String key, T value);
  T getObject(String key);

}
