package com.m3kong.infrastructure.persistence.repository;

import com.m3kong.domain.model.dto.*;
import com.m3kong.domain.model.enums.StatusType;
import com.m3kong.domain.repository.CategoryRepository;
import com.m3kong.infrastructure.persistence.mapper.CategoryJpaRepository;
import com.m3kong.infrastructure.persistence.mapper.CategoryLocalizationJpaRepository;
import com.m3kong.infrastructure.persistence.model.CategoryEntity;
import com.m3kong.infrastructure.persistence.model.CategoryLocalizationEntity;
import com.m3kong.infrastructure.persistence.repository.converter.CategoryEntityToDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

  final private CategoryJpaRepository categoryJpaRepository;
  final private CategoryLocalizationJpaRepository categoryLocalizationJpaRepository;

  @Override
  public Flux<CategoryDto> getActiveCategoriesByOrder() {
    return categoryJpaRepository.findByStatusOrderByDisplayOrderAsc(StatusType.Active)
            .map((data) -> CategoryEntityToDto.convert(data, categoryLocalizationJpaRepository.findByCategoryId(data.getId()).collectList().block()));
  }

  @Override
  public Mono<CategoryDto> createCategory(CreateCategoryDto categoryDto) {

    Set<CategoryLocalizationEntity> localizations = categoryDto.getLocalizations().stream()
            .map(localizationDto -> CategoryLocalizationEntity.builder()
                    .content(localizationDto.getContent())
                    .languageCode(localizationDto.getLanguageCode())
                    .build())
            .collect(Collectors.toSet());
    categoryLocalizationJpaRepository.saveAll(localizations);

    CategoryEntity entity = CategoryEntity.builder()
            .name(categoryDto.getName())
            .status(categoryDto.getStatusType().ordinal())
            .displayOrder(categoryDto.getDisplayOrder())
            .accessRule(categoryDto.getAccessRule().ordinal())
            .createTime(new Date())
            .modifyTime(new Date())
            .build();


    return categoryJpaRepository.save(entity).map(data -> CategoryEntityToDto.convert(data, localizations));
  }

  private Set<CategoryLocalizationEntity> updateLocalizations(Map<String, Integer> languageCodeMapIds, Set<CategoryLocalizationDto> localizations) {
    return localizations.stream()
            .map(localizationDto -> CategoryLocalizationEntity.builder()
                    .id(languageCodeMapIds.get(localizationDto.getLanguageCode()))
                    .content(localizationDto.getContent())
                    .languageCode(localizationDto.getLanguageCode())
                    .build())
            .collect(Collectors.toSet());
  }

  private CategoryEntity updateEntity(CategoryEntity entity, CategoryDto categoryDto) {

    // save category entity
    entity.setName(categoryDto.getName());
    entity.setStatus(categoryDto.getStatus());
    entity.setDisplayOrder(categoryDto.getDisplayOrder());
    entity.setAccessRule(categoryDto.getAccessRule());
    entity.setModifyTime(new Date());
    categoryJpaRepository.save(entity);

    // save category localization entity
    Map<String, Integer> languageCodeMapIds = new HashMap<>();
    categoryLocalizationJpaRepository.findByCategoryId(entity.getId())
            .doOnEach(localizationEntity -> languageCodeMapIds.put(localizationEntity.get().getLanguageCode(), localizationEntity.get().getId()));
    Set<CategoryLocalizationEntity> localizations = updateLocalizations(languageCodeMapIds, categoryDto.getLocalizations());
    categoryLocalizationJpaRepository.saveAll(localizations);

    return entity;
  }

  @Override
  public Mono<CategoryDto> updateCategory(CategoryDto categoryDto) {
    if (categoryDto.getId() == 0) {
      return Mono.error(new IllegalArgumentException("Category id is required"));
    }

    return categoryJpaRepository.findById(categoryDto.getId())
            .map(entity -> updateEntity(entity, categoryDto))
            .flatMap(categoryJpaRepository::save)
            .map(data -> CategoryEntityToDto.convert(data, categoryLocalizationJpaRepository.findByCategoryId(data.getId()).collectList().block()));
  }

  @Override
  public Flux<CategoryDto> updateCategoryIndexes(UpdateCategoryIndexDto indexDto) {

    Map<Integer, Integer> mapCategoryIndex = indexDto.getIndexes().stream()
            .collect(Collectors.toMap(CategoryIndexDto::getCategoryId, CategoryIndexDto::getDisplayOrder));

    return categoryJpaRepository.findAllById(mapCategoryIndex.keySet()).map(entity -> {
      entity.setDisplayOrder(mapCategoryIndex.get(entity.getId()));
      categoryJpaRepository.save(entity);
      return entity;
    }).flatMap(categoryJpaRepository::save).map(data -> CategoryEntityToDto.convert(data, categoryLocalizationJpaRepository.findByCategoryId(data.getId()).collectList().block()));
  }

  @Override
  public Mono<Void> deleteCategory(Integer categoryId) {
    return categoryJpaRepository.deleteById(categoryId).then();
  }
}
