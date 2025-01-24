package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.AccessRuleType;
import jakarta.persistence.AttributeConverter;

public class AccessRuleConverter implements AttributeConverter<AccessRuleType, Integer> {
  @Override
  public Integer convertToDatabaseColumn(AccessRuleType type) {
    return type.ordinal();
  }

  @Override
  public AccessRuleType convertToEntityAttribute(Integer dbData) {
    if (AccessRuleType.values().length <= dbData) {
      return AccessRuleType.Pending;
    }

    return AccessRuleType.values()[dbData];
  }
}
