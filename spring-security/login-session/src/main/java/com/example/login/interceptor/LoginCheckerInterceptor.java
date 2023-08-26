package com.example.login.interceptor;

import com.example.login.controller.httpsession.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckerInterceptor implements HandlerInterceptor {

  // 로그인한 유저인지를 체크한다.
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    if (session == null || session.getAttribute(SessionConst.SESSION_MEMBER) == null) {
      // 미인증 일 경우
      response.sendRedirect("/");
      return false;
    }
    return true;
  }
}
