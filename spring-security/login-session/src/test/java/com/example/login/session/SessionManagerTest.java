package com.example.login.session;

import com.example.login.controller.session.SessionUser;
import com.example.login.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class SessionManagerTest {

  private SessionManager sessionManager;
  private User user;

  @BeforeEach
  void init() {
    user = User.builder()
            .id("user1")
            .pwd("user1")
            .build();
    this.sessionManager = new SessionManager();
  }

  @Test
  @DisplayName("세션 생성 후 응답 쿠키 헤더에 SessionId 가 담겨져 있다.")
  void createSession() {
    // when
    MockHttpServletResponse response = new MockHttpServletResponse();
    sessionManager.createSession(user, response);

    // then
    assertThat(response.getCookie("mySessionId")).isNotNull();
  }

  @Test
  @DisplayName("요청 헤더에 SessionId 가 있을 경우 세션아이디와 일치하는 유저를 조회한다.")
  void getSession() {
    // given
    MockHttpServletResponse response = new MockHttpServletResponse();
    sessionManager.createSession(user, response);
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCookies(response.getCookies());


    // when
    SessionUser sessionUser = sessionManager.getSession(request);


    // then
    assertThat(sessionUser.getId()).isEqualTo(user.getId());
  }

  @Test
  @DisplayName("Session 을 제거하면 Request 로 Session 을 조회할 수 없다. null")
  void expireSession() {
    // given
    MockHttpServletResponse response = new MockHttpServletResponse();
    sessionManager.createSession(user, response);
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCookies(response.getCookies());

    // when
    String mySessionId = response.getCookie("mySessionId").getValue();
    sessionManager.expireSession(mySessionId);

    // then
    assertThat(sessionManager.getSession(request)).isNull();
  }
}