package com.example.login.interceptor;

import com.example.login.controller.httpsession.SessionConst;
import com.example.login.controller.session.SessionUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginCheckerInterceptorTest {

  @Autowired
  private MockMvc mvc;


  @Test
  @DisplayName("로그인 인증이 필요한 경로에 세션 없이 접근 하면 응답상태 302 반환")
  void shouldReturn302WhenAccessAllowedUrlWithNoSession() throws Exception {
    //session 이 존재한다면, LoginCheckerInterceptor 가 호출된다.

    mvc.perform(get("/http-session/home"))
            .andExpect(status().is3xxRedirection());

  }

  @Test
  @DisplayName("로그인 인증이 필요한 경로에 세션 있으면 응답상태 200 반환")
  void shouldReturn200WhenAccessAllowedUrlWithSession() throws Exception {
    //session 이 존재한다면, LoginCheckerInterceptor 가 호출된다.

    mvc.perform(get("/http-session/home"))
            .andExpect(status().is3xxRedirection());

  }
}