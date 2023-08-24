package com.example.login.controller.httpsession;

import com.example.login.controller.request.LoginRequestDto;
import com.example.login.controller.session.SessionUser;
import com.example.login.user.User;
import com.example.login.user.UserStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/http-session")
public class HttpSessionController {

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
    // 로그인 로직
    User user = UserStore.getInstance().findById(loginRequestDto.id());
    user.checkPwd(loginRequestDto.getPassword());

    // session 생성
    HttpSession session = request.getSession(true);
    session.setAttribute(SessionConst.SESSION_MEMBER, new SessionUser(user.getId()));
    return ResponseEntity.ok("login success");
  }

  @GetMapping("/home")
  public ResponseEntity<?> home(
          @SessionAttribute(value = SessionConst.SESSION_MEMBER) SessionUser sessionUser) {
    if (sessionUser == null) {
      ResponseEntity.badRequest().body("can't allowed");
    }
    return ResponseEntity.ok().body("Hello " + sessionUser.id());
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return ResponseEntity.badRequest().body("no session, cant logout");
    }
    SessionUser sessionUser = (SessionUser) session.getAttribute(SessionConst.SESSION_MEMBER);
    session.invalidate();
    return ResponseEntity.ok("success logout " + sessionUser.getId());
  }
}
