package com.example.loginsession;

import com.example.loginsession.exception.PasswordNotMatchedException;
import lombok.Builder;
import lombok.Getter;

public record User(@Getter String id, @Getter String pwd) {
  @Builder
  public User {
  }

  public void checkPwd(String inputPwd) {
    if (!this.pwd.equals(inputPwd)) {
      throw new PasswordNotMatchedException("not equal password");
    }
  }
}
