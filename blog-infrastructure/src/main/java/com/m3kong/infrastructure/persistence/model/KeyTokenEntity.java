package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(schema = "auth", name = "key_token")
public class KeyTokenEntity {

  @Id
  private int id;

  private UUID keyId;

  private String publicKey;

  private String refreshToken;

  private Date expiredTime;

  private int status;

  public StatusType getStatus() {
    return StatusType.values()[status];
  }

  public void setStatus(StatusType status) {
    this.status = status.ordinal();
  }

  private Date createTime;

  private UUID accountId;
}
