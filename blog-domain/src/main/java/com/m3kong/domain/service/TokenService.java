package com.m3kong.domain.service;

import com.m3kong.domain.model.dto.TokenDto;

import java.util.UUID;

public interface TokenService {

  TokenDto createToken(UUID accountId) throws Exception;

  boolean validateToken(String jwtToken) throws Exception;

}
