package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.infrastructure.persistence.model.CategoryLocalizationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoryLocalizationJpaRepository extends ReactiveCrudRepository<CategoryLocalizationEntity, Integer> {
  Flux<CategoryLocalizationEntity> findByCategoryId(Integer categoryId);
}
