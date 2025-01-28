package com.m3kong.infrastructure.cache.distributed.redis.impl;

import com.m3kong.infrastructure.cache.distributed.redis.RedisInfraService;
import com.m3kong.infrastructure.utils.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisInfraServiceImpl implements RedisInfraService {

  @Resource
  private RedisTemplate<String, Object> redisTemplate;


  @Override
  public void setString(String key, String value) {
    if (StringUtils.hasLength(key)) { // null or ''
      return;
    }
    redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public String getString(String key) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(key))
            .map(String::valueOf)
            .orElse(null);
  }

  @Override
  public void setObject(String key, Object value) {
    if (StringUtils.hasLength(key)) { // null or ''
      return;
    }

    try {
      redisTemplate.opsForValue().set(key, value);
    }catch (Exception e){
      log.error("setObject error:{}",e.getMessage());
    }
  }

  @Override
  public <T> T getObject(String key, Class<T> targetClass) {
    Object result = redisTemplate.opsForValue().get(key);

    return ObjectUtil.getObject(result, targetClass, log);
  }

  @Override
  public void put(String key, Object value, long timeout, TimeUnit unit) {
    if (StringUtils.hasLength(key)) { // null or ''
      return;
    }

    try {
      redisTemplate.opsForValue().set(key, value, timeout, unit);
    }catch (Exception e){
      log.error("setObject with TTL error:{}",e.getMessage());
    }
  }
}
