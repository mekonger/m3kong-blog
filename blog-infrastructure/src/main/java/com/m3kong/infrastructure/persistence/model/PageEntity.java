package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.AccessRuleType;
import com.m3kong.domain.model.enums.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(schema = "posting", name = "page")
public class PageEntity {

  @Id
  private int id;

  private String title;

  private String slug;

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
}
