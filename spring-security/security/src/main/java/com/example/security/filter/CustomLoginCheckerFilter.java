package com.example.security.filter;

import com.example.security.login.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomLoginCheckerFilter extends AbstractAuthenticationProcessingFilter {

  private static final String DEFAULT_LOGIN_REQUEST_URL = "/login";
  private static final String HTTP_METHOD = "POST";
  private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER =
          new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD); //=>   /login 의 요청에, POST로 온 요청에 매칭된다.


  private final ObjectMapper objectMapper;

  public CustomLoginCheckerFilter(ObjectMapper objectMapper) {
    super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER);   // 위에서 설정한  /oauth2/login/* 의 요청에, GET으로 온 요청을 처리하기 위해 설정한다.
    this.objectMapper = objectMapper;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
    LoginDto loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), LoginDto.class);
    String username = loginDto.getUsername();
    String password = loginDto.getPassword();
    log.info("username: {}, password: {}", username, password);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    return this.getAuthenticationManager().authenticate(authenticationToken);
  }
}

