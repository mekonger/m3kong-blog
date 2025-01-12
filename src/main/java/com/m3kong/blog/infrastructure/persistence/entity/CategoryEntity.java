package com.m3kong.blog.infrastructure.persistence.entity;

import com.m3kong.blog.domain.enums.AccessRuleType;
import com.m3kong.blog.domain.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "posting", name = "category")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  @Convert(converter = AccessRuleConverter.class)
  private AccessRuleType accessRule;

  private Date createTime;

  private Date modifyTime;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<CategoryLocalizationEntity> localizations;

}