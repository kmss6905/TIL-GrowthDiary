package com.example.login.controller.session;

import com.example.login.controller.request.LoginRequestDto;
import com.example.login.session.SessionManager;
import com.example.login.user.User;
import com.example.login.user.UserStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionLoginController {

  private final SessionManager sessionManager;

  @PostMapping("/session/login")
  public String sessionLogin(
          @RequestBody LoginRequestDto loginRequestDto,
          HttpServletResponse reponse
  ) {
    // 1. 로그인 로직
    User user = UserStore.getInstance().findById(loginRequestDto.id());
    user.checkPwd(loginRequestDto.getPassword());

    // 2. 세션 생성
    sessionManager.createSession(user, reponse);
    return "ok";
  }
}
