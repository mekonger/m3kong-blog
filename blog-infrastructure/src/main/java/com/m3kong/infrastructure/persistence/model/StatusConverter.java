package com.m3kong.infrastructure.persistence.model;

import com.m3kong.blog.domain.enums.StatusType;
import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<StatusType, Integer> {
  @Override
  public Integer convertToDatabaseColumn(StatusType status) {
    return status.ordinal();
  }

  @Override
  public StatusType convertToEntityAttribute(Integer dbData) {
    if (StatusType.values().length <= dbData) {
      return StatusType.Inactive;
    }

    return StatusType.values()[dbData];
  }
}
