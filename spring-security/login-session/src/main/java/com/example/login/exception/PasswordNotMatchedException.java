package com.example.login.exception;

public class PasswordNotMatchedException extends RuntimeException{

  public PasswordNotMatchedException(String message) {
    super(message);
  }
}
