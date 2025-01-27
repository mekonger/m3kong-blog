package com.m3kong.domain.repository;

import com.m3kong.domain.model.dto.CategoryDto;
import com.m3kong.domain.model.dto.CreateCategoryDto;
import com.m3kong.domain.model.dto.UpdateCategoryIndexDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository {

  Flux<CategoryDto> getActiveCategoriesByOrder();

  Mono<CategoryDto> createCategory(CreateCategoryDto categoryDto);

  Mono<CategoryDto> updateCategory(CategoryDto categoryDto);

  Flux<CategoryDto> updateCategoryIndexes(UpdateCategoryIndexDto indexDto);

  Mono<Void> deleteCategory(Integer categoryId);
}
