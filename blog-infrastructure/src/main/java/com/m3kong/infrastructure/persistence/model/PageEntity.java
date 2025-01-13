package com.m3kong.infrastructure.persistence.model;

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
@Table(schema = "posting", name = "page")
public class PageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String title;

  private String slug;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  @Convert(converter = AccessRuleConverter.class)
  private AccessRuleType accessRule;

  private Date createTime;

  private Date modifyTime;

  @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PageLocalizationEntity> localizations;

  @ManyToMany(mappedBy = "pages")
  private Set<KeywordEntity> keywords;
}
