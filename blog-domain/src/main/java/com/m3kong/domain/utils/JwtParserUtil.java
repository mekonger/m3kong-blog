package com.m3kong.domain.utils;

import java.util.Base64;

public class JwtParserUtil {

  /**
   * Decode JWT token payload to extract claims.
   *
   * @param token The JWT token
   * @return The payload as a JSON string
   */
  private static String decodeJwtPayload(String token) {
    try {
      // Split the JWT token into parts (header.payload.signature)
      String[] parts = token.split("\\.");
      if (parts.length < 2) {
        throw new IllegalArgumentException("Invalid JWT token");
      }

      // Decode the payload (second part of JWT)
      String payload = parts[1];
      byte[] decodedBytes = Base64.getUrlDecoder().decode(payload);
      return new String(decodedBytes);
    } catch (Exception e) {
      throw new RuntimeException("Failed to decode JWT payload", e);
    }
  }

  /**
   * Extract a specific claim from the JWT payload.
   *
   * @param jsonPayload The JWT payload in JSON format
   * @param claimKey    The key of the claim to extract
   * @return The value of the claim, or null if not found
   */
  private static String extractClaimFromJson(String jsonPayload, String claimKey) {
    // Very simple JSON parsing for the claim (replace with Gson/Jackson for production use)
    String key = "\"%s\":\"".formatted(claimKey);
    int startIndex = jsonPayload.indexOf(key);
    if (startIndex == -1) {
      return null; // Claim not found
    }
    startIndex += key.length();
    int endIndex = jsonPayload.indexOf("\"", startIndex);
    return jsonPayload.substring(startIndex, endIndex);
  }


  /**
   * Extract subject from JWT token payload.
   *
   * @param token The JWT token
   * @return The subject from the payload
   */
  private static String getSubjectFromJwt(String token) throws Exception {
    String payload = decodeJwtPayload(token);
    return extractClaimFromJson(payload, "sub");
  }

  /**
   * Extract JWT ID ("jti") from JWT token.
   *
   * @param token The JWT token
   * @return The JWT ID ("jti") from the payload
   */
  public static String getJtiFromJwt(String token) {
    String payload = decodeJwtPayload(token);
    return extractClaimFromJson(payload, "jti");
  }

}
