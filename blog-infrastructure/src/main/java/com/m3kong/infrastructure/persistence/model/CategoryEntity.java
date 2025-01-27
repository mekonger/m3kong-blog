package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.AccessRuleType;
import com.m3kong.domain.model.enums.StatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "posting", name = "category")
public class CategoryEntity {

  @Id
  private int id;

  private String name;

  private int status;

  public StatusType getStatus() {
    return StatusType.values()[status];
  }

  public void setStatus(StatusType status) {
    this.status = status.ordinal();
  }

  private int accessRule;

  public AccessRuleType getAccessRule() {
    return AccessRuleType.values()[accessRule];
  }

  public void setAccessRule(AccessRuleType accessRule) {
    this.accessRule = accessRule.ordinal();
  }

  private Date createTime;

  private Date modifyTime;

  private int displayOrder;

}
