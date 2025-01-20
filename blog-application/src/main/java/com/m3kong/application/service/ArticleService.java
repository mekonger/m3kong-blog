package com.m3kong.application.service;

import com.m3kong.application.dto.ArticleDto;
import reactor.core.publisher.Flux;

public interface ArticleService {

  Flux<ArticleDto> getListArticles();
}
