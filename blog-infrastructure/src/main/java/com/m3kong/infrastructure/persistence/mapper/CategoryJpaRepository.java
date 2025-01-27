package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.infrastructure.persistence.model.CategoryEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CategoryJpaRepository extends ReactiveCrudRepository<CategoryEntity, Integer> {

  @Query("SELECT * FROM posting.category WHERE status = $1 ORDER BY display_order ASC")
  Flux<CategoryEntity> findByStatusOrderByDisplayOrderAsc(int status);
}
