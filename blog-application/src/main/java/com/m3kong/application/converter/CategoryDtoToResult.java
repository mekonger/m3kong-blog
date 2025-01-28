package com.m3kong.application.converter;

import com.m3kong.application.Utils.SlugUtil;
import com.m3kong.application.model.dto.Category;
import com.m3kong.domain.model.dto.CategoryLocalizationDto;

public class CategoryDtoToResult {

  public static Category convert(com.m3kong.domain.model.dto.CategoryDto categoryDto, String languageCode) {
    if (categoryDto == null || categoryDto.getLocalizations().isEmpty()) {
      return null;
    }

    CategoryLocalizationDto categoryLocalizationDto = categoryDto.getLocalizations().stream().filter(localization -> localization.getLanguageCode().equals(languageCode.toLowerCase())).findFirst().orElse(null);
    if (categoryLocalizationDto == null) {
      return null;
    }

    return new Category(categoryDto.getId(), categoryDto.getName(), categoryLocalizationDto.getContent(), categoryLocalizationDto.getLanguageCode(), SlugUtil.toSlug(categoryDto.getName()));
  }
}
