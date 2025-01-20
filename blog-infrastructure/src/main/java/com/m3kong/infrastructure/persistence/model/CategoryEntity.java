package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.AccessRuleType;
import com.m3kong.domain.model.enums.StatusType;
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
