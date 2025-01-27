package com.m3kong.infrastructure.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(schema = "posting", name = "keyword")
public class KeywordEntity {

  @Id
  private int id;

  private String content;

  private Date createTime;
}
