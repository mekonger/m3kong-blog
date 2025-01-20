package com.m3kong.application.service.impl;

import com.m3kong.application.dto.ArticleDto;
import com.m3kong.application.service.ArticleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Override
  public Flux<ArticleDto> getListArticles() {
    return Flux.fromIterable(List.of(
            new ArticleDto(1, "Article 1", "Short description 1", new Date()),
            new ArticleDto(2, "Article 2", "Short description 2", new Date()),
            new ArticleDto(3, "Article 3", "Short description 3", new Date()),
            new ArticleDto(4, "Article 4", "Short description 4", new Date()),
            new ArticleDto(5, "Article 5", "Short description 5", new Date())
    ));
  }
}
