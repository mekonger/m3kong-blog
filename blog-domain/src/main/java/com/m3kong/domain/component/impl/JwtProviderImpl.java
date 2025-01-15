package com.m3kong.domain.component.impl;

import com.m3kong.domain.component.JwtProvider;
import com.m3kong.domain.model.dto.TokenDto;
import com.m3kong.domain.utils.PublicKeyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProviderImpl implements JwtProvider {

  private PublicKey publicKey = null;
  private PrivateKey privateKey = null;

  private final long JWT_EXPIRATION = 1000 * 60 * 60; // 1 hour
  private final long REFRESH_JWT_EXPIRATION = 1000 * 60 * 60 * 24; // 1 day expiration


  private void generateRSAKeyPair() throws Exception {
    // Generate RSA key pair
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048); // Set key size
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    // Extract public and private keys
    privateKey = keyPair.getPrivate();
    publicKey = keyPair.getPublic();
  }

  private String createAccessToken(String keyId, PrivateKey privateKey, String subject, long expireAt) {
    return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(new Date(expireAt))
            .setId(keyId)
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact();
  }

  public static boolean validateToken(String token, PublicKey publicKey) {
    try {
      // Parse and validate the JWT token
      Jwts.parser()
              .setSigningKey(publicKey) // Set public key for verification
              .parseClaimsJws(token);   // Parse and validate the token
      return true; // Token is valid
    } catch (Exception e) {
      System.err.printf("Invalid JWT token: %s%n", e.getMessage());
      return false; // Token is invalid
    }
  }


  @Override
  public TokenDto createToken(UUID accountId, String tokenKeyId) throws Exception {

    generateRSAKeyPair();

    long now = System.currentTimeMillis();
    String accessToken = createAccessToken(tokenKeyId, privateKey, accountId.toString(), now + JWT_EXPIRATION);
    String refreshToken = createAccessToken(tokenKeyId, privateKey, accessToken, now + REFRESH_JWT_EXPIRATION);

    TokenDto result = new TokenDto(accessToken, refreshToken, PublicKeyUtil.publicKeyToString(publicKey), new Date(now + JWT_EXPIRATION));

    publicKey = null;
    privateKey = null;

    return result;
  }

  @Override
  public boolean checkValidToken(String jwtToken, String publicKey) {

    PublicKey publicKeyObject = PublicKeyUtil.stringToPublicKey(publicKey);

    return validateToken(jwtToken, publicKeyObject);
  }
}
