package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.infrastructure.persistence.model.CategoryLocalizationEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoryLocalizationJpaRepository extends ReactiveCrudRepository<CategoryLocalizationEntity, Integer> {

  @Query("SELECT * from posting.category_localization where category_id = $1")
  Flux<CategoryLocalizationEntity> findByCategoryId(int categoryId);
}
