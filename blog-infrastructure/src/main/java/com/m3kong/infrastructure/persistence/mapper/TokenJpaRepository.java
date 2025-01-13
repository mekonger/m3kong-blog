package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.blog.domain.enums.StatusType;
import com.m3kong.blog.infrastructure.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TokenJpaRepository extends ReactiveCrudRepository<TokenEntity, Integer> {

  @Query("select tk from TokenEntity tk where tk.account.id=:acId and tk.status=:st order by tk.createTime desc")
  Flux<TokenEntity> findByAccountIdAndStatus(@Param("acId") UUID accountId, @Param("st") StatusType status);
}
