package com.m3kong.domain.exceptions;

public class BaseAppException extends RuntimeException {
  private int code;
  private String message;

  public BaseAppException(int code, String message) {
    super(message);
    this.code = code;
  }

  public BaseAppException(String message) {
    super(message);
    this.code = 0;
  }
}
