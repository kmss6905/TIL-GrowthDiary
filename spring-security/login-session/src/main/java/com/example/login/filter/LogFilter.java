package com.example.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String uuid = UUID.randomUUID().toString();
    try {
      log.info("REQUEST start = [{}][{}]", uuid, httpRequest.getRequestURI());
      chain.doFilter(request, response);
      log.info("REQUEST end = [{}][{}]", uuid, httpRequest.getRequestURI());
    } catch (Exception e) {
      throw e;
    }finally {

    }
  }
}
