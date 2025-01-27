package com.m3kong.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryLocalizationDto {
  private String languageCode;
  private String content;
}
