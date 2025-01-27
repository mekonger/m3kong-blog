package com.m3kong.application.converter;

import com.m3kong.application.dto.CategoryResult;
import com.m3kong.domain.model.dto.CategoryDto;
import com.m3kong.domain.model.dto.CategoryLocalizationDto;

public class CategoryDtoToResult {

  public static CategoryResult convert(CategoryDto categoryDto, String languageCode) {
    if (categoryDto == null || categoryDto.getLocalizations().isEmpty()) {
      return null;
    }

    CategoryLocalizationDto categoryLocalizationDto = categoryDto.getLocalizations().stream().filter(localization -> localization.getLanguageCode().equals(languageCode.toLowerCase())).findFirst().orElse(null);
    if (categoryLocalizationDto == null) {
      return null;
    }

    return new CategoryResult(categoryDto.getId(), categoryDto.getName(), categoryLocalizationDto.getContent(), categoryLocalizationDto.getLanguageCode());
  }
}
