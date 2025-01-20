package com.m3kong.interfaces.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ViewResolverChecker {
  @Autowired
  private ApplicationContext applicationContext;

  public void printViewResolvers() {
    String[] resolvers = applicationContext.getBeanNamesForType(org.springframework.web.reactive.result.view.ViewResolver.class);
    System.out.println("Registered ViewResolvers: " + Arrays.toString(resolvers));
  }
}
