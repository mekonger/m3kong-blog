package com.m3kong.infrastructure.persistence.model;

import com.m3kong.blog.domain.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "auth", name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String hashPassword;

  private String userName;

  private String email;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  private Date createTime;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<TokenEntity> tokens;

  @ManyToMany(mappedBy = "accounts")
  private Set<PermissionEntity> permissions;

}
