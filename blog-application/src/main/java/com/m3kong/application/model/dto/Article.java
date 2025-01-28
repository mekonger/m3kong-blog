package com.m3kong.application.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Article {

  private int id;
  private String title;
  private String shortDesc;
  private Date publishAt;
}
