package com.example.login.controller.session;

import com.example.login.controller.request.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SessionLoginControllerTest {


  @Autowired
  private MockMvc mvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  void init() {
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("로그인에 성공하면 응답 헤더에 mySessionId 키로 세션Id 가 있다.")
  void sessionLogin() throws Exception {
    LoginRequestDto requestDto = new LoginRequestDto("user1", "user1");
    String loginRequestBody = objectMapper.writeValueAsString(requestDto);

    mvc.perform(post("/session/login")
                    .content(loginRequestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(cookie().exists("mySessionId"))
    ;
  }

  @Test
  @DisplayName("로그인 한 후 홈페이지 이동할 수 있다.")
  void sessionHome() throws Exception {
    LoginRequestDto requestDto = new LoginRequestDto("user1", "user1");
    String loginRequestBody = objectMapper.writeValueAsString(requestDto);

    MvcResult loginResult = mvc.perform(post("/session/login")
                    .content(loginRequestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    Cookie mySessionCookie = loginResult.getResponse().getCookie("mySessionId");


    // 새로운 요청 객체를 사용하여 홈 페이지 요청
    mvc.perform(get("/session/home")
                    .cookie(mySessionCookie))
            .andExpect(status().isOk());
  }


  @Test
  @DisplayName("로그인 하지 않으면 홈으로 이동할 수 없다.")
  void sessionLogout() throws Exception {
    mvc.perform(get("/session/home"))
            .andExpect(status().isUnauthorized());
  }
}