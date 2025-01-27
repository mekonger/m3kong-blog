package com.m3kong.interfaces.controller.html;

import com.m3kong.application.dto.ArticleDto;
import com.m3kong.application.dto.CategoryResult;
import com.m3kong.application.service.ArticleService;
import com.m3kong.interfaces.service.IndexService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@Controller
@AllArgsConstructor
public class BlogController {

  final private ArticleService articleService;
  final private IndexService indexService;

  @GetMapping("/")
  public Mono<String> home(Model model) {
    Flux<ArticleDto> articleDtoFlux = articleService.getListArticles();
    Flux<CategoryResult> categoryResultFlux = indexService.getActiveCategoriesByOrder("vi");

    // Combine two Flux using zip
    Mono<Map<String, Object>> combinedData = Mono.zip(articleDtoFlux.collectList(), categoryResultFlux.collectList())
            .map(tuple -> {
              Map<String, Object> data = new HashMap<>();
              data.put("articles", tuple.getT1());
              data.put("categories", tuple.getT2());
              return data;
            });

    // Pass combined data to Thymeleaf
    return combinedData
            .flatMap(data -> {
              model.addAllAttributes(data);
              return Mono.just("index");
            });
  }
}
