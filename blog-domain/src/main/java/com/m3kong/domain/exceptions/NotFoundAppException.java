package com.m3kong.domain.exceptions;

public class NotFoundAppException extends BaseAppException {

  public NotFoundAppException() {
    super(404, "Not found");
  }
}
