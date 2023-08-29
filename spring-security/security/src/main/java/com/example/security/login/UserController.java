package com.example.security.login;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

  @GetMapping("/")
  public String home(HttpSession session) {

    // SecurityContextHolder
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // HttpSession
    SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    Authentication authentication1 = context.getAuthentication();

    assert authentication == authentication1; // 같은 객체이다.
    return "home";
  }

  @GetMapping("/thread")
  public String thread() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("parent authentication is will be null? {}", authentication == null);
    new Thread(new Runnable() {
      @Override
      public void run() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("child thread's authentication is will be null? {}", authentication == null);
      }
    }).start();
    return "thread";
  }


}
