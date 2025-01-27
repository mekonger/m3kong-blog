package com.m3kong.domain.model.dto;

import com.m3kong.domain.model.enums.AccessRuleType;
import com.m3kong.domain.model.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CategoryDto {
  private int id;
  private String name;
  private StatusType status;
  private AccessRuleType accessRule;
  private int displayOrder;
  private Set<CategoryLocalizationDto> localizations;
}
