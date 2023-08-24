package com.example.login.session;

import com.example.login.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionManagerTest {

  @Test
  void 세션생성() {
    User user = User.builder()
            .id("user1")
            .pwd("user1")
            .build();

    SessionManager sessionManager = new SessionManager();
    String sessionId = sessionManager.createSession(user);

    assertThat(sessionId).isNotNull();
    assertThat(sessionId).isNotEmpty();



  }

  @Test
  void 세션조회() {
    // when
    User user = User.builder()
            .id("user1")
            .pwd("user1")
            .build();
    SessionManager sessionManager = new SessionManager();
    String sessionId = sessionManager.createSession(user);

    // given
    User sessionUser = sessionManager.getSession(sessionId);

    // then
    assertThat(sessionUser.id()).isEqualTo(user.getId());
  }

  @Test
  void 세션삭제() {
    // when
    User user = User.builder()
            .id("user1")
            .pwd("user1")
            .build();
    SessionManager sessionManager = new SessionManager();
    String sessionId = sessionManager.createSession(user);


    // given
    sessionManager.expireSession(sessionId);

    // then
    assertThatThrownBy(() -> sessionManager.getSession(sessionId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("no session, go to login page");
  }
}