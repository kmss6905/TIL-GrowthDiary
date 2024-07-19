package test.payment.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class TransactionLoggingFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      final String correlationId = getCorrelationId(request);
      MDC.put("X-Correlation-Id", correlationId);
      response.setHeader("X-Correlation-Id", correlationId);
      filterChain.doFilter(request, response);
    }finally {
      MDC.remove("X-Correlation-Id");
    }
  }

  private String getCorrelationId(HttpServletRequest request) {
    // payment
    if (request.getRequestURI().equals("/success")
        || request.getRequestURI().equals("/fail")
        || request.getRequestURI().equals("/cancel"))
    {
      String correlationId = request.getParameter("correlation_id");
      if (correlationId != null) {
        return correlationId;
      }
    }

    String correlationId = request.getHeader("X-Correlation-Id");
    if (correlationId != null) {
      return correlationId;
    }
    return getOrGenerateCorrelationId();
  }

  private String getOrGenerateCorrelationId() {
    return UUID.randomUUID().toString();
  }
}
