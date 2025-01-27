package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "posting", name = "category_localization")
public class CategoryLocalizationEntity {

  @Id
  private int id;

  private String content;

  private String languageCode;

  private int status;

  public StatusType getStatus() {
    return StatusType.values()[status];
  }

  public void setStatus(StatusType status) {
    this.status = status.ordinal();
  }

  private LocalDateTime createTime;

  private LocalDateTime modifyTime;

  private Integer categoryId;
}
