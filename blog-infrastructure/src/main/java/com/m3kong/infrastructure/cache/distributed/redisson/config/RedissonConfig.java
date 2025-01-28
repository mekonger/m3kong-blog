package com.m3kong.infrastructure.cache.distributed.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

  @Value("${redisson.address}")
  private String redisAddress;

  @Value("${redisson.connectionPoolSize}")
  private int connectionPoolSize;

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress(redisAddress).setConnectionPoolSize(connectionPoolSize).setDatabase(0);

    return Redisson.create(config);
  }

}
