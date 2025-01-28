package com.m3kong.interfaces.service;

import com.m3kong.application.model.dto.Category;
import reactor.core.publisher.Flux;

public interface IndexService {

  Flux<Category> getActiveCategoriesByOrder(String languageCode, Long version);
}
