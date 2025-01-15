package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.domain.model.enums.StatusType;
import com.m3kong.infrastructure.persistence.model.KeyTokenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface KeyTokenJpaRepository extends ReactiveCrudRepository<KeyTokenEntity, Integer> {

  @Query("select tk from KeyTokenEntity tk where tk.account.id=:acId and tk.status=:st order by tk.createTime desc")
  Flux<KeyTokenEntity> findByAccountIdAndStatus(@Param("acId") UUID accountId, @Param("st") StatusType status);
}
