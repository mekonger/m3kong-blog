package com.m3kong.application.service;

import com.m3kong.application.model.dto.Article;
import reactor.core.publisher.Flux;

public interface ArticleService {

  Flux<Article> getListArticles();
}
