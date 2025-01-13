package com.m3kong.infrastructure.persistence.model;

import com.m3kong.blog.domain.enums.AccessRuleType;
import com.m3kong.blog.domain.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "posting", name = "page_localization")
public class PageLocalizationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String content;

  private String languageCode;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  private Date createTime;

  private Date modifyTime;

  @ManyToOne
  @JoinColumn(columnDefinition = "page_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PageEntity page;
}
