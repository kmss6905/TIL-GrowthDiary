package com.example.login.argument;

import com.example.login.annotation.Login;
import com.example.login.controller.httpsession.SessionConst;
import com.example.login.controller.session.LoginUser;
import com.example.login.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(LoginUser.class)
            && parameter.getAnnotatedElement().equals(Login.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest httpRequest = (HttpServletRequest) webRequest;
    HttpSession session = httpRequest.getSession();
    if (session == null && session.getAttribute(SessionConst.SESSION_MEMBER) == null) {
      throw new BadRequestException("잘못된 접근 입니다.");
    }
    return session.getAttribute(SessionConst.SESSION_MEMBER);
  }
}
