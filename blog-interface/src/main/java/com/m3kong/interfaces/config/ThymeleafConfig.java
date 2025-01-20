package com.m3kong.interfaces.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig {

  @Bean
  public ClassLoaderTemplateResolver templateResolver() {
    ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
    resolver.setPrefix("templates/"); // Tương ứng với src/main/resources/templates/
    resolver.setSuffix(".html");     // Định dạng file
    resolver.setTemplateMode("HTML");
    resolver.setCacheable(false);    // Disable cache trong quá trình phát triển
    return resolver;
  }

  @Bean
  public SpringWebFluxTemplateEngine templateEngine(ClassLoaderTemplateResolver templateResolver) {
    SpringWebFluxTemplateEngine engine = new SpringWebFluxTemplateEngine();
    engine.setTemplateResolver(templateResolver);
    return engine;
  }

  @Bean
  public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver(SpringWebFluxTemplateEngine templateEngine) {
    ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
    viewResolver.setTemplateEngine(templateEngine);
    viewResolver.setDefaultCharset(java.nio.charset.StandardCharsets.UTF_8);

    return viewResolver;
  }

}
