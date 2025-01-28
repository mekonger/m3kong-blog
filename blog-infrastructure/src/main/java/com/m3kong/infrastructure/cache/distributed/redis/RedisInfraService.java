package com.m3kong.infrastructure.cache.distributed.redis;

import java.util.concurrent.TimeUnit;

public interface RedisInfraService {

  void setString(String key, String value);
  String getString(String key);

  void setObject(String key, Object value);
  <T> T getObject(String key, Class<T> targetClass);

  void put(String key, Object value, long timeout, TimeUnit unit);
}
