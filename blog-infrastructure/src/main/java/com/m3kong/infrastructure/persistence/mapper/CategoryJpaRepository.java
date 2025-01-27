package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.domain.model.enums.StatusType;
import com.m3kong.infrastructure.persistence.model.CategoryEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CategoryJpaRepository extends ReactiveCrudRepository<CategoryEntity, Integer> {

  @Query("select c from CategoryEntity c where c.status=:status order by c.displayOrder asc")
  Flux<CategoryEntity> findByStatusOrderByDisplayOrderAsc(StatusType status);
}
