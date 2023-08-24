package com.example.login.session;

import com.example.login.user.User;
import org.springframework.stereotype.Component;

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
  private static final ConcurrentHashMap<String, User> sessions = new ConcurrentHashMap<>();

  public String createSession(User user) {
    String sessionId = UUID.randomUUID().toString();
    sessions.put(sessionId, user);
    return sessionId;
  }

  public User getSession(String sessionId) {
    if (sessions.get(sessionId) == null) {
      throw new IllegalArgumentException("no session, go to login page");
    }
    return sessions.get(sessionId);
  }

  public void expireSession(String sessionId) {
    sessions.remove(sessionId);
  }
}
