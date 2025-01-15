package com.m3kong.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TokenDto {

  private String accessToken;
  private String refreshToken;
  private String publicKey;
  private Date expiredAt;

}
