package test.payment.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import test.payment.Order;
import test.payment.client.interceptor.TracePropagationClientHttpRequestInterceptor;
import test.payment.kakao.Payment;

@Component
public class KakaoClient {

  @Value("${payment.kakao.cid}")
  private String cid;

  private final RestClient restClient;

  public KakaoClient(
      RestClient.Builder webClientBuilder,
      @Value("${payment.kakao.secret}") String secretKey,
      @Value("${payment.kakao.baseurl}") String baseUrl
  ) {
    this.restClient = webClientBuilder
        .defaultHeaders((headers -> {
          headers.add("Authorization", "SECRET_KEY " + secretKey);
          headers.add("Content-Type", "application/json");
        }))
        .requestFactory(new BufferingClientHttpRequestFactory(new JdkClientHttpRequestFactory()))
        .requestInterceptor(new TracePropagationClientHttpRequestInterceptor())
        .baseUrl(baseUrl)
        .build();
  }

  public ResponseEntity<String> ready(Order order) {
    Map<String, Object> body = new HashMap<>();
    body.put("cid", cid);
    body.put("partner_order_id", order.getOrderId());
    body.put("partner_user_id", "hotelking_admin");
    body.put("item_name", order.getName());
    body.put("quantity", "1");
    body.put("total_amount", order.getPrice());
    body.put("vat_amount", "200");
    body.put("tax_free_amount", "0");
    body.put("approval_url", "http://localhost:8080/success?order_key=" + order.getOrderId() + "&correlation_id=" + MDC.get("X-Correlation-Id"));
    body.put("fail_url", "http://localhost:8080/fail?order_key=" + order.getOrderId() + "&correlation_id=" + MDC.get("X-Correlation-Id"));
    body.put("cancel_url", "http://localhost:8080/cancel?order_key=" + order.getOrderId() + "&correlation_id=" + MDC.get("X-Correlation-Id"));
    return restClient.post()
        .uri( "/ready")
        .contentType(APPLICATION_JSON)
        .body(body)
        .retrieve()
        .toEntity(String.class);
  }

  public ResponseEntity<String> approve(Payment payment, String pgToken) {
    Map<String, Object> body = new HashMap<>();
    body.put("cid", cid);
    body.put("tid", payment.getTid());
    body.put("pg_token", pgToken);
    body.put("partner_order_id", payment.getOrder().getOrderId());
    body.put("partner_user_id", "hotelking_admin");
    return restClient.post()
        .uri( "/approve")
        .contentType(APPLICATION_JSON)
        .body(body)
        .retrieve()
        .toEntity(String.class);
  }
}
