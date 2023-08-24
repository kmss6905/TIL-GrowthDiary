package com.example.login.controller.session;

import com.example.login.controller.request.LoginRequestDto;
import com.example.login.session.SessionManager;
import com.example.login.user.User;
import com.example.login.user.UserStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
          HttpServletResponse response
  ) {
    // 1. 로그인 로직
    User user = UserStore.getInstance().findById(loginRequestDto.id());
    user.checkPwd(loginRequestDto.getPassword());

    // 2. 세션 생성
    sessionManager.createSession(user, response);
    return "ok";
  }

  @GetMapping("/session/home")
  public ResponseEntity<String> sessionHome(HttpServletRequest request) {
    SessionUser sessionUser = sessionManager.getSession(request);
    // 세션을 찾지 못했다면 401 에러
    if (sessionUser == null) {
      return ResponseEntity.status(401)
              .body("not allowed request");
    }
    return ResponseEntity.ok(String.format("hello %s", sessionUser.getId()));
  }

  @PostMapping("/session/logout")
  public ResponseEntity<String> sessionLogout(HttpServletRequest request, HttpServletResponse response) {
    sessionManager.expireSession(request, response);
    return ResponseEntity.ok().body("logout success");
  }
}
