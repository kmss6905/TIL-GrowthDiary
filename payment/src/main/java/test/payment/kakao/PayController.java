package test.payment.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import test.payment.Order;
import test.payment.OrderRepository;
import test.payment.client.KakaoClient;

/**
 * Q0. 주문생성시점?
 * Q1. 결제 취소는 어디에 두는 것이 맞을까? 위치 (예약이 가능한 지 체크하는 로직 1 위치)
 * Q2. 결제 취소가 실패하는 경우? -> 여러번 시도? while?
 * Q3. 임계구역 = room schedule 테이블의 row 들인 경우에 어떻게 redis key 를 만들까
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PayController {

  private final OrderRepository orderRepository;
  private final PaymentRepository paymentRepository;
  private final ObjectMapper objectMapper;
  private final KakaoClient kakaoClient;

  @Value("${payment.kakao.secret}")
  private String kakaoSecret;

  @Value("${payment.kakao.cid}")
  private String kakaoCid;

  @GetMapping("/success")
  public ResponseEntity<String> successPayment(
      @RequestParam(name = "pg_token") String pgToken,
      @RequestParam(name = "order_key") String orderKey,
      @RequestParam(name = "correlation_id") String correlationId
  ) {
    log.info("pg token = {}, order_key = {}, correlation_id = {}", pgToken, orderKey, correlationId);
    log.info("결제 성공");
    Payment payment = findPayment(orderKey);

    // TODO: 마지막으로 order key 를 이용해서 -> 예약이 가능한 지 체크하는 로직
    // ready 할 때 저장해놓은 TID 로 승인 요청(payment 에 tid 있음)
    // Pg 사 요청 + 상품 id 와 재고를 차감하는 부분의 락
    // 상품 전체 x, 각 상품에 대해서 락
    ResponseEntity<String> response = requestApprove(pgToken, payment);

    // 2xx 승인 성공
    if (response.getStatusCode().is2xxSuccessful()) {
      // TODO: 마지막으로 order key 를 이용해서 -> 예약이 가능한 지 체크하는 로직 2
      log.info("결제 승인");
      /*
      실패하는 경우에 대한 케이스 ? 어떻게 할지? 공부!! client. fallback, retry -> rest client, 다양한 케이스
      선정 이유

      여기어때의 경우 이곳에서 확인 후 이미 찼다면 환불하는 로직이 들어가는 것 같다.
      추측. 돈이 빠져나갔다는 카카오톡 메시지를 받은 이후 -> 결제가 취소되었다고 메시지 옴

      // 임계구역 = room schedule 테이블의 row 들
      // 1. hotel id ? -> x
      // 2. checkin-checkout 으로 구성 ->
      if(남아있는방없음){
         // 결제 취소 및 내역 저장
         // 결제 취소 요청이 실패하면 ?? -> 계속 시도 ? if -> if -> if or while ?
         throw new RuntimeException(e);
      }

       */
      payment.changeToComplete();
      return ResponseEntity.ok(response.getBody());
    }

    // 4xx, 5xx 승인 실패
    throw new RuntimeException("결제가 실패하였습니다.");
  }

  private ResponseEntity<String> requestApprove(String pgToken, Payment payment) {
    String secretKey = kakaoSecret;

    // Create headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "SECRET_KEY " + secretKey);
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create request body
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("cid", kakaoCid);
    requestBody.put("tid", payment.getTid());
    requestBody.put("pg_token", pgToken);
    requestBody.put("partner_order_id", payment.getOrder().getOrderId());
    requestBody.put("partner_user_id", "hotelking_admin");

    // Create entity
    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

    // Create RestTemplate
    RestTemplate restTemplate = new RestTemplate();


    // Set URL
    String url = "https://open-api.kakaopay.com/online/v1/payment/approve";

    // Execute request
    ResponseEntity<String> response = restTemplate.exchange(
        UriComponentsBuilder.fromHttpUrl(url).build().toUri(),
        HttpMethod.POST,
        entity,
        String.class
    );
    return response;
  }

  private Payment findPayment(String orderId) {
    return paymentRepository.findByOrderId(UUID.fromString(orderId))
        .orElseThrow(() -> new RuntimeException("결제 에러 발생"));
  }

  // 결제 실패 callback
  @GetMapping("/fail")
  public ResponseEntity<String> failPayment(
      @RequestParam(name = "order_key") UUID orderKey,
      @RequestParam(name = "correlation_id") String correlationId
  ) {
    log.info("결제 실패 : {}", orderKey);
    Payment payment = findPayment(orderKey.toString());
    payment.fail();

    // TODO : 언제 실패 되었는 지 저장 ?
    return ResponseEntity.ok("결제가 실패되었습니다.");
  }

  // 결제 취소 callback
  @GetMapping("/cancel")
  public ResponseEntity<String> cancelPayment(
      @RequestParam(name = "order_key") UUID orderKey,
      @RequestParam(name = "correlation_id") String correlationId
  ) {
    log.info("결제 취소 : {}", orderKey);
    Payment payment = findPayment(orderKey.toString());
    payment.cancel();

    // TODO : 언제 취소 되었는 지 저장 ?
    return ResponseEntity.ok("결제가 취소되었습니다.");
  }

  @GetMapping("/ready")
  public ResponseEntity<?> readyPayment(@RequestParam(name = "order_key") UUID orderKey) {
    // 1. orderKey 로 주문번호 확인
    Order order = findOrder(orderKey);


    // 2. kakao pay 결제 준비 요청
    ResponseEntity<String> response = kakaoClient.ready(order);

    // - kakao pay 결제 준비 요청 실패 -> 예외 (주문은 그대로 있음)
    if (!response.getStatusCode().is2xxSuccessful()) {
      // - 실패 내용 저장 ? payment 객체가 없기 때문에 어딘가에 저장? 추척하기 위해서는 필요하겠지?.. 그런데 어디에다가?.. 필요하나?
      throw new RuntimeException("kakao pay 결제 준비 요청 실패");
    }

    try {
      PaymentReadyApiResponse paymentReadyApiResponse = objectMapper.readValue(response.getBody(), PaymentReadyApiResponse.class);

      // 3. kakao pay 결제 준비 요청 응답 저장
      // - 결제 객체 저장( order : payment = 1 : N ), 같은 주문에 대해서 여러 번 payment 요청 가능할 수 있기 때문
      // - tid : 결제 승인 시 필수
      Payment payment = new Payment(paymentReadyApiResponse.getTid(), PayStatus.READY, order);
      paymentRepository.save(payment);

      // 4. 클라이언트에 결제 승인 응답
      return ResponseEntity.status(200).body(paymentReadyApiResponse);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private Order findOrder(UUID orderKey) {
    return orderRepository.findById(orderKey)
        .orElseThrow(() -> new RuntimeException("not found order key"));
  }
}
