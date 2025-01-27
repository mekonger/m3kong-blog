package com.m3kong.infrastructure.persistence.repository.converter;

import com.m3kong.domain.model.dto.CategoryLocalizationDto;
import com.m3kong.infrastructure.persistence.model.CategoryLocalizationEntity;

public class CategoryLocalizationEntityToDto {
    public static CategoryLocalizationDto convert(CategoryLocalizationEntity categoryLocalizationEntity) {
        return new CategoryLocalizationDto(categoryLocalizationEntity.getLanguageCode(), categoryLocalizationEntity.getContent());
    }
}
