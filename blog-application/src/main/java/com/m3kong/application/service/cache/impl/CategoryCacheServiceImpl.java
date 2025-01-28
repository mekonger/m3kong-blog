package com.m3kong.application.service.cache.impl;

import com.m3kong.application.converter.CategoryDtoToResult;
import com.m3kong.application.model.cache.ListCategoryCache;
import com.m3kong.application.model.dto.Category;
import com.m3kong.application.service.cache.CategoryCacheService;
import com.m3kong.domain.service.CategoryDomainService;
import com.m3kong.infrastructure.cache.distributed.redis.RedisInfraService;
import com.m3kong.infrastructure.cache.distributed.redisson.RedisDistributedLocker;
import com.m3kong.infrastructure.cache.distributed.redisson.RedisDistributedService;
import com.m3kong.infrastructure.cache.local.CaffeineCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryCacheServiceImpl implements CategoryCacheService {

  final private RedisDistributedService redisDistributedService;
  final private RedisInfraService redisInfraService;
  final private CaffeineCache<ListCategoryCache> caffeineCache;
  final private CategoryDomainService categoryDomainService;

  @Override
  public Mono<List<Category>> getListCategory(String languageCode, Long version) {
    // 1 - get data from local cache
    Mono<ListCategoryCache> localCache = getListCategoryLocalCache(languageCode);
    if (localCache == null) {
      return getListCategoryDistributedCache(languageCode).map(ListCategoryCache::getCategories);
    }

    return localCache.publishOn(Schedulers.boundedElastic()).map(data -> {
      // 1. YES
      if (data != null) {
        // User:version, cache:version
        // 1. version = null
        if (version == null) {
          log.info("01: GET CATEGORY FROM LOCAL CACHE: versionUser:{}, versionLocal: {}", version, data.getVersion());
          return data.getCategories();
        }

        if (version <= data.getVersion()) {
          log.info("02: GET CATEGORY FROM LOCAL CACHE: versionUser:{}, versionLocal: {}", version, data.getVersion());
          return data.getCategories();
        }

        // load from distributed cache
        ListCategoryCache distributedCache = getListCategoryDistributedCache(languageCode).block();
        return distributedCache != null ? distributedCache.getCategories() : null;
      }

      // load from distributed cache
      ListCategoryCache distributedCache = getListCategoryDistributedCache(languageCode).block();
      return distributedCache != null ? distributedCache.getCategories() : null;
    });
  }

  private Mono<ListCategoryCache> getCategoryListFromDatabase(String languageCode) {

    RedisDistributedLocker locker = redisDistributedService.getDistributedLock(genCategoryListKeyLock(languageCode));

    try {
      // 1 - Make lock
      boolean isLock = locker.tryLock(1, 5, TimeUnit.SECONDS);
      // Warning: Always unlock by any case
      if (!isLock) {
        return null; // return retry
      }

      // Get cache again before load from database
      ListCategoryCache listCategoryCache = redisInfraService.getObject(genCategoryListKey(languageCode), ListCategoryCache.class);

      // 2. YES
      if (listCategoryCache != null) {
        return Mono.just(listCategoryCache);
      }

      log.info("GET CATEGORY LIST FROM DATABASE");
      return categoryDomainService.getActiveCategoriesByOrder()
        .map(data -> CategoryDtoToResult.convert(data, languageCode))
        .collectList()
        .map(categories -> {
          ListCategoryCache data = new ListCategoryCache().withClone(categories).withVersion(System.currentTimeMillis());
          // set data to distributed cache
          redisInfraService.setObject(genCategoryListKey(languageCode), data);
          return data;
        });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      locker.unlock();
    }
  }

  /**
   * get category list from local cache
   */
  public Mono<ListCategoryCache> getListCategoryLocalCache(String languageCode) {
    // get cache from Caffeine
    ListCategoryCache data = caffeineCache.getObject(genLocalCategoryListKey(languageCode));
    return data != null ? Mono.just(data) : null;
  }

  /**
   * get category list from distributed cache
   */
  public Mono<ListCategoryCache> getListCategoryDistributedCache(String languageCode) {
    // 1 - get data
    ListCategoryCache listCategoryCache = redisInfraService.getObject(genCategoryListKey(languageCode), ListCategoryCache.class);
    if(listCategoryCache == null){
      log.info("GET CATEGORY LIST FROM DISTRIBUTED LOCK");
      Mono<ListCategoryCache> data = getCategoryListFromDatabase(languageCode);

      // set into local cache
      if (data != null) {
        data.subscribe(cache -> {
          // lock()
          log.info("CACHE INTO LOCAL DATABASE");
          caffeineCache.setObject(genLocalCategoryListKey(languageCode), cache); // consistency cache
          // unLock()
        }); // consistency cache
        // unLock()
        log.info("GET TICKET FROM DISTRIBUTED CACHE");
      }

      return data;
    }

    // 2 - put data to local cache
    // lock()
    caffeineCache.setObject(genLocalCategoryListKey(languageCode), listCategoryCache); // consistency cache
    // unLock()
    log.info("GET TICKET FROM DISTRIBUTED CACHE");
    return Mono.just(listCategoryCache);
  }

  private String genLocalCategoryListKey(String languageCode) {
    return "LOCAL_CATEGORY:LIST:" + languageCode.toUpperCase();
  }

  private String genCategoryListKey(String languageCode) {
    return "PRO_CATEGORY:LIST:" + languageCode.toUpperCase();
  }

  private String genCategoryListKeyLock(String languageCode) {
    return "PRO_LOCK_KEY_CATEGORY:LIST:" + languageCode.toUpperCase();
  }
}
