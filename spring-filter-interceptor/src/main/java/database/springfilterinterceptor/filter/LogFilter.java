package database.springfilterinterceptor.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
//@Component
//@Order(2)
public class LogFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
      UUID requestId = UUID.randomUUID();
      log.info("LogFilter [REQUEST] {}", requestId);
      try {
        filterChain.doFilter(request, response);
      } finally {
        log.info("LogFilter [RESPONSE] {}", requestId);
      }
  }
}
