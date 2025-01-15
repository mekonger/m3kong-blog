package com.m3kong.domain.component;

import com.m3kong.domain.model.dto.TokenDto;

import java.util.UUID;

public interface JwtProvider {

  TokenDto createToken(UUID accountId, String tokenKeyId) throws Exception;

  boolean checkValidToken(String jwtToken, String publicKey);

}
