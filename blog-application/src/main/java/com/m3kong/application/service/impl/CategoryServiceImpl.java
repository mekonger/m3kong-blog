package com.m3kong.application.service.impl;

import com.m3kong.application.converter.CategoryDtoToResult;
import com.m3kong.application.dto.CategoryResult;
import com.m3kong.application.service.CategoryService;
import com.m3kong.domain.service.CategoryDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  final private CategoryDomainService categoryDomainService;

  @Override
  public Flux<CategoryResult> getActiveCategoriesByOrder(String languageCode) {

    return categoryDomainService.getActiveCategoriesByOrder()
        .map(data -> CategoryDtoToResult.convert(data, languageCode));
  }
}
