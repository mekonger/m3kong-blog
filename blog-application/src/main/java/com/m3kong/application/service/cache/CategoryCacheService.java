package com.m3kong.application.service.cache;

import com.m3kong.application.model.dto.Category;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryCacheService {
  Mono<List<Category>> getListCategory(String languageCode, Long version);
}
