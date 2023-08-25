package com.example.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.PatternMatchUtils;

public class StudyTest {
  private static final String[] whitelist = {"/http-session/login"};

  @Test
  public void urlMatchTest() throws Exception {
    // given
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setRequestURI("/http-session/login");


    // when, then
    Assertions.assertThat(PatternMatchUtils.simpleMatch(whitelist, request.getRequestURI())).isTrue();
  }
}
