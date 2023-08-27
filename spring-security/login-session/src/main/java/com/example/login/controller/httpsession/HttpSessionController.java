package com.example.login.controller.httpsession;

import com.example.login.annotation.Login;
import com.example.login.controller.request.LoginRequestDto;
import com.example.login.controller.session.SessionUser;
import com.example.login.controller.session.LoginUser;
import com.example.login.user.User;
import com.example.login.user.UserStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
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

  @GetMapping("/my-info")
  public ResponseEntity<?> myInfo(@Login LoginUser loginUser) {
    return ResponseEntity.ok(loginUser.getId() + " 입니다~");
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

  @GetMapping("/session-info")
  public ResponseEntity<String> sessionInfo(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return ResponseEntity.ok("세션이 없습니다.");
    }


    // 세션 데이터 출력
    session.getAttributeNames().asIterator()
            .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));

    log.info("sessionId={}", session.getId());
    log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
    log.info("creationTime={}", new Date(session.getCreationTime()));
    log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
    log.info("isNew={}", session.isNew());

    return ResponseEntity.ok("hello session info");
  }
}
