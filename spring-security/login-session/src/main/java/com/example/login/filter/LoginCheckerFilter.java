package com.example.login.filter;

import com.example.login.controller.httpsession.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckerFilter implements Filter {

  private static final String[] whitelist = {"/http-session/login", "/http-session/session-info"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String requestURI = httpRequest.getRequestURI();
    if (isLoginCheckPath(requestURI)) {
      try {
        log.info("인증 체크 필터 시작 {}", requestURI);

        // 세션이 없거나, 있더라도 찾고자 하는 값이 없는 경우
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute(SessionConst.SESSION_MEMBER) == null) {
            log.info("미인증 사용자 요청 {}", requestURI);
            // 로그인으로 redirect
            httpResponse.sendRedirect("/login"); // REST API 의 경우에는 다르게 적용
            return;
        }
        chain.doFilter(request, response);
      } catch (Exception e) {
        throw e; // 톰켓까지 예외를 보내주어야 한다.
      } finally {
        log.info("인증 체크 필터 종료 {}", requestURI);
      }
    }
    chain.doFilter(request, response);
  }

  private boolean isLoginCheckPath(String requestURI) {
    return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }
}
