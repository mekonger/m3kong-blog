package com.m3kong.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResult {
  private int id;
  private String name;
  private String localizeName;
  private String localizeCode;
}
