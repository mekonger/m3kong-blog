package com.m3kong.domain.service;

import com.m3kong.domain.model.dto.CategoryDto;
import com.m3kong.domain.model.dto.CreateCategoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryDomainService {

  Flux<CategoryDto> getActiveCategoriesByOrder();
  Mono<CategoryDto> createCategory(CreateCategoryDto categoryDto);
  Mono<CategoryDto> updateCategory(CategoryDto categoryDto);
  Mono<Void> deleteCategory(Integer categoryId);
}
