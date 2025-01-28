package com.m3kong.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ObjectUtil {

  /**
   * Tính trọng lượng của một object dựa trên kích thước byte của nó.
   *
   * @param obj Đối tượng cần tính trọng lượng.
   * @return Trọng lượng (kích thước byte) của object.
   */
  public static int calculateObjectSizeInBytes(Object obj) {
    if (obj == null) {
      return 0; // Trả về 0 nếu đối tượng là null
    }

    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

      // Ghi object vào byte array
      objectOutputStream.writeObject(obj);

      // Lấy kích thước byte của đối tượng
      return byteArrayOutputStream.size();

    } catch (IOException e) {
      throw new RuntimeException("Error calculating object size in bytes", e);
    }
  }

  /**
   * Convert ve instatance cua 1 class
   *
   * @param result
   * @param targetClass
   * @return
   * @param <T>
   */
  public static  <T> T getObject(Object result, Class<T> targetClass, Logger log) {
    if (null == result) {
      return null;
    }

    // Nếu kết quả là một LinkedHashMap
    if (result instanceof Map) {
      try {
        // Chuyển đổi LinkedHashMap thành đối tượng mục tiêu
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(result, targetClass);
      } catch (IllegalArgumentException e) {
        log.error("Error converting LinkedHashMap to object: {}", e.getMessage());
        return null;
      }
    }

    // Nếu result là String, thực hiện chuyển đổi bình thường
    if (result instanceof String) {
      try {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue((String) result, targetClass);
      } catch (JsonProcessingException e) {
        log.error("Error deserializing JSON to object: {}", e.getMessage());
        return null;
      }
    }

    return null;
  }

}
