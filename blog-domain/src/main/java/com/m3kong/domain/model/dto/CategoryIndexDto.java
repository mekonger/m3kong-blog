package com.m3kong.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryIndexDto {
  private Integer categoryId;
  private Integer displayOrder;
}
