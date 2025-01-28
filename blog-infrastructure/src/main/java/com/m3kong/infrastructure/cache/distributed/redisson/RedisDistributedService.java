package com.m3kong.infrastructure.cache.distributed.redisson;

public interface RedisDistributedService {
  RedisDistributedLocker getDistributedLock(String lockKey);
}
