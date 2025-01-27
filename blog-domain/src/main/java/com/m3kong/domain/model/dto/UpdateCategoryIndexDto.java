package com.m3kong.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateCategoryIndexDto {
  private List<CategoryIndexDto> indexes;
}
