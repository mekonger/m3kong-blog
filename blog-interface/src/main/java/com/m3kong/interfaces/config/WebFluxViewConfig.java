package com.m3kong.interfaces.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring6.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
@AllArgsConstructor
public class WebFluxViewConfig implements WebFluxConfigurer {

  private final ThymeleafReactiveViewResolver thymeleafReactiveViewResolver;

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.viewResolver(thymeleafReactiveViewResolver);
  }
}