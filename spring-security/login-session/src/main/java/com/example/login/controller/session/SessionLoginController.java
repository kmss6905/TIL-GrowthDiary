package com.example.login.controller.session;

import com.example.login.controller.request.LoginRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionLoginController {

  @PostMapping("/session/login")
  public String sessionLogin(@RequestBody LoginRequestDto loginRequestDto) {
    // 1. 로그인 로직

    // 2. 세션 생성
    return "ok";
  }
}
