package com.m3kong.infrastructure.persistence.repository.converter;

import com.m3kong.domain.model.dto.CategoryDto;
import com.m3kong.infrastructure.persistence.model.CategoryEntity;
import com.m3kong.infrastructure.persistence.model.CategoryLocalizationEntity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryEntityToDto {
    public static CategoryDto convert(CategoryEntity categoryEntity, Collection<CategoryLocalizationEntity> localizations) {
        return new CategoryDto(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getStatus(), categoryEntity.getAccessRule(), categoryEntity.getDisplayOrder(), localizations.stream().map(CategoryLocalizationEntityToDto::convert).collect(Collectors.toSet()));
    }
}
