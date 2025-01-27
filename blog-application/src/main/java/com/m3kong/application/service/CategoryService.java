package com.m3kong.application.service;

import com.m3kong.application.dto.CategoryResult;
import reactor.core.publisher.Flux;

public interface CategoryService {

  Flux<CategoryResult> getActiveCategoriesByOrder(String languageCode);
}
