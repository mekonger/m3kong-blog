package com.m3kong.infrastructure.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "posting", name = "keyword")
public class KeywordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String content;

  private Date createTime;

  @ManyToMany
  @JoinTable(
    name = "posting.page_keyword",
    joinColumns = @JoinColumn(name = "keyword_id"),
    inverseJoinColumns = @JoinColumn(name = "page_id")
  )
  private Set<PageEntity> pages;
}
