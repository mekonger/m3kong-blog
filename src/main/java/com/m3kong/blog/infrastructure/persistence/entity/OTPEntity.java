package com.m3kong.blog.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "auth", name = "otp")
public class OTPEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String code;

  private String verifyToken;

  private Date expiredTime;

  private Date createTime;

}
