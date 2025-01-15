package com.m3kong.domain.utils;

import com.m3kong.domain.exceptions.InvalidAppException;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PublicKeyUtil {

  /**
   * Converts a PublicKey to a Base64-encoded String (PEM format).
   *
   * @param publicKey The PublicKey to convert.
   * @return The Base64-encoded public key string.
   */
  public static String publicKeyToString(PublicKey publicKey) {
    // Convert public key to bytes and encode in Base64
    byte[] encodedPublicKey = publicKey.getEncoded();
    String base64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
    return "-----BEGIN PUBLIC KEY-----\n%s\n-----END PUBLIC KEY-----".formatted(base64PublicKey);
  }

  /**
   * Converts a Base64-encoded String back to a PublicKey.
   *
   * @param publicKeyStr The Base64-encoded public key string.
   * @return The reconstructed PublicKey object.
   */
  public static PublicKey stringToPublicKey(String publicKeyStr) {
    try {
      // Remove PEM headers
      String base64PublicKey = publicKeyStr
              .replace("-----BEGIN PUBLIC KEY-----", "")
              .replace("-----END PUBLIC KEY-----", "")
              .replaceAll("\\s", ""); // Remove all whitespace

      // Decode Base64 and reconstruct the PublicKey
      byte[] decodedKey = Base64.getDecoder().decode(base64PublicKey);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePublic(keySpec);
    } catch (Exception e) {
      throw new InvalidAppException("Error converting String to PublicKey");
    }
  }

}
