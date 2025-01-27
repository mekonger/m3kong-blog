package com.m3kong.domain.service.impl;

import com.m3kong.domain.model.dto.CategoryDto;
import com.m3kong.domain.model.dto.CreateCategoryDto;
import com.m3kong.domain.repository.CategoryRepository;
import com.m3kong.domain.service.CategoryDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CategoryDomainServiceImpl implements CategoryDomainService {

  final private CategoryRepository categoryRepository;

  @Override
  public Flux<CategoryDto> getActiveCategoriesByOrder() {
    return categoryRepository.getActiveCategoriesByOrder();
  }

  @Override
  public Mono<CategoryDto> createCategory(CreateCategoryDto categoryDto) {
    return categoryRepository.createCategory(categoryDto);
  }

  @Override
  public Mono<CategoryDto> updateCategory(CategoryDto categoryDto) {
    return categoryRepository.updateCategory(categoryDto);
  }

  @Override
  public Mono<Void> deleteCategory(Integer categoryId) {
    return categoryRepository.deleteCategory(categoryId);
  }
}
