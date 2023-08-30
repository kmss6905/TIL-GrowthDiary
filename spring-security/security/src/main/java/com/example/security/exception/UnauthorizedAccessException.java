package com.example.security.exception;

public class UnauthorizedAccessException extends RuntimeException {
  public UnauthorizedAccessException(String s) {
    super(s);
  }
}
