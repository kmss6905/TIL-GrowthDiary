package test.payment.client.interceptor;

import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class TracePropagationClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  private static final Logger log = LoggerFactory.getLogger(
      TracePropagationClientHttpRequestInterceptor.class);

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    HttpHeaders headers = request.getHeaders();
    headers.set("X-Correlation-Id", MDC.get("X-Correlation-Id"));
    log.info("request uri: {}, request method: {}, request headers: {}, request body: {}",request.getURI(), request.getMethod(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
    ClientHttpResponse response = execution.execute(request, body);
    log.info("response status code: {}, response headers: {}, response body: {}", response.getStatusCode(), response.getHeaders(), StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
    return response;
  }
}
