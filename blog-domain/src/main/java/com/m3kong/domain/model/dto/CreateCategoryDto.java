package com.m3kong.domain.model.dto;

import com.m3kong.domain.model.enums.AccessRuleType;
import com.m3kong.domain.model.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateCategoryDto {

  private String name;
  private int displayOrder;
  private AccessRuleType accessRule;
  private StatusType statusType;
  private List<CategoryLocalizationDto> localizations;
}
