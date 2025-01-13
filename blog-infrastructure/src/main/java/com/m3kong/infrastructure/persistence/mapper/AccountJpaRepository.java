package com.m3kong.infrastructure.persistence.mapper;

import com.m3kong.blog.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountJpaRepository extends ReactiveCrudRepository<AccountEntity, UUID> {

  @Query("select ac from AccountEntity ac where ac.userName=:un")
  Mono<AccountEntity> findFirstByUserName(@Param("un") String userName);

  @Query("select ac from AccountEntity ac where ac.email=:em")
  Mono<AccountEntity> findFirstByEmail(@Param("em") String email);

}
