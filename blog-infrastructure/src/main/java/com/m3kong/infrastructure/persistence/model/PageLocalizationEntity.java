package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(schema = "posting", name = "page_localization")
public class PageLocalizationEntity {

  @Id
  private int id;

  private String content;

  private String languageCode;

  private int status;

  public void setStatus(StatusType status) {
    this.status = status.ordinal();
  }

  public StatusType getStatus() {
    return StatusType.values()[status];
  }

  private Date createTime;

  private Date modifyTime;

  private Integer pageId;
}
