package com.m3kong.application.service;

import com.m3kong.application.model.dto.Category;
import reactor.core.publisher.Flux;

public interface CategoryService {

  Flux<Category> getActiveCategoriesByOrder(String languageCode, Long version);
}
