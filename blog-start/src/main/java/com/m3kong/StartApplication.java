package com.m3kong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.Arrays;

@EnableWebFlux
@EnableR2dbcRepositories
@SpringBootApplication
public class StartApplication implements CommandLineRunner {

  @Autowired
  private ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }

  @Override
  public void run(String... args) {
    String[] resolvers = applicationContext.getBeanNamesForType(org.springframework.web.reactive.result.view.ViewResolver.class);
    System.out.println("Registered ViewResolvers: " + Arrays.toString(resolvers));
  }
}
