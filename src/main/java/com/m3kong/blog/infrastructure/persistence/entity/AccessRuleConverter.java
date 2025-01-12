package com.m3kong.blog.infrastructure.persistence.entity;

import com.m3kong.blog.domain.enums.AccessRuleType;
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
