package com.m3kong.interfaces.service.impl;

import com.m3kong.application.dto.CategoryResult;
import com.m3kong.application.service.CategoryService;
import com.m3kong.interfaces.service.IndexService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

  final private CategoryService categoryService;

  @Override
  public Flux<CategoryResult> getActiveCategoriesByOrder(String languageCode) {
    return categoryService.getActiveCategoriesByOrder(languageCode);
  }
}
