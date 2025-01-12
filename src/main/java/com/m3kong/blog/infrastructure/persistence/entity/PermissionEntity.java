package com.m3kong.blog.infrastructure.persistence.entity;

import com.m3kong.blog.domain.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "auth", name = "permission")
public class PermissionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String groupName;

  private String code;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  private Date createTime;

  @ManyToMany
  @JoinTable(
    name = "auth.account_permission",
    joinColumns = @JoinColumn(name = "permission_id"),
    inverseJoinColumns = @JoinColumn(name = "account_id")
  )
  private Set<AccountEntity> accounts;

}
