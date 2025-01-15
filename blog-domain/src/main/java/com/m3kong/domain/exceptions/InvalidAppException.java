package com.m3kong.domain.exceptions;

public class InvalidAppException extends BaseAppException {

  public InvalidAppException() {
    super(500, "Invalid exception");
  }

  public InvalidAppException(String message) {
    super(500, message);
  }
}
