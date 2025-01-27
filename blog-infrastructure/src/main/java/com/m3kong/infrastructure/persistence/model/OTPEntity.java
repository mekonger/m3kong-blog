package com.m3kong.infrastructure.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(schema = "auth", name = "otp")
public class OTPEntity {

  @Id
  private int id;

  private String code;

  private String verifyToken;

  private Date expiredTime;

  private Date createTime;

}
