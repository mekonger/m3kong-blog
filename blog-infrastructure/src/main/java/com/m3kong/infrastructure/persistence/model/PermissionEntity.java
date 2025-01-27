package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Table(schema = "auth", name = "permission")
public class PermissionEntity {

  @Id
  private int id;

  private String name;

  private String groupName;

  private String code;

  private int status;

  public StatusType getStatus() {
    return StatusType.values()[status];
  }

  public void setStatus(StatusType status) {
    this.status = status.ordinal();
  }

  private Date createTime;
}
