package com.m3kong.application.service.impl;

import com.m3kong.application.model.dto.Category;
import com.m3kong.application.service.CategoryService;
import com.m3kong.application.service.cache.CategoryCacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  final private CategoryCacheService categoryCacheService;

  @Override
  public Flux<Category> getActiveCategoriesByOrder(String languageCode, Long version) {
    log.info("Implement Application : {}, {}: ", languageCode, version);

    Mono<List<Category>> listCategoryCache = categoryCacheService.getListCategory(languageCode, version);
    return listCategoryCache.flatMapMany(Flux::fromIterable);
  }
}
