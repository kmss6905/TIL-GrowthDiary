package com.example.login.session;

import com.example.login.controller.session.SessionUser;
import com.example.login.exception.BadRequestException;
import com.example.login.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. 세션생성
 * 2. 새션조회
 * 3. 세션삭제
 */
@Component
public class SessionManager {

  // 메모리, 혹은 외부 DB에 세션을 저장 할 수 있다.
  final ConcurrentHashMap<String, SessionUser> sessions = new ConcurrentHashMap<>();
  public static final String SESSION_ID = "mySessionId";

  public void createSession(User user, HttpServletResponse response) {
    String sessionId = UUID.randomUUID().toString();
    sessions.put(sessionId, new SessionUser(user.getId()));
    response.addCookie(new Cookie(SESSION_ID, sessionId));
  }
  public SessionUser getSession(HttpServletRequest request) {
    Cookie findCookie = findCookie(request, SESSION_ID);
    if (findCookie == null) {
      return null;
    }
    return this.sessions.get(findCookie.getValue());
  }

  public void expireSession(HttpServletRequest request, HttpServletResponse response) {
    Cookie cookie = findCookie(request, "mySessionId");
    if (cookie == null) {
      throw new BadRequestException("can't logout");
    }

    // 세션 삭제
    sessions.remove(cookie.getValue());

    // 쿠키 삭제
    Cookie expireCookie = createExpireCookie();
    response.addCookie(expireCookie);
  }

  private static Cookie createExpireCookie() {
    Cookie removedCookie = new Cookie("mySessionId", null);
    removedCookie.setMaxAge(0);
    return removedCookie;
  }

  private Cookie findCookie(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      return null;
    }
    return Arrays.stream(cookies)
            .filter(it -> it.getName().equals(cookieName))
            .findFirst()
            .orElse(null);

  }
}
