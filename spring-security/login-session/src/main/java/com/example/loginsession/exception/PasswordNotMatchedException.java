package com.example.loginsession.exception;

public class PasswordNotMatchedException extends RuntimeException{

  public PasswordNotMatchedException(String message) {
    super(message);
  }
}
