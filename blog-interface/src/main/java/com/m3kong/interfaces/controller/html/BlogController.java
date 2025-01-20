package com.m3kong.interfaces.controller.html;

import com.m3kong.application.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;


@Controller
@AllArgsConstructor
public class BlogController {

  final private ArticleService articleService;

  @GetMapping("/")
  public Mono<String> home(Model model) {
    return articleService.getListArticles()
            .collectList()
            .doOnNext(articles -> model.addAttribute("articles", articles))
            .then(Mono.just("index"));
  }
}
