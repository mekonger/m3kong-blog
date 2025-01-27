package com.m3kong.interfaces.service;

import com.m3kong.application.dto.CategoryResult;
import reactor.core.publisher.Flux;

public interface IndexService {

  Flux<CategoryResult> getActiveCategoriesByOrder(String languageCode);
}
