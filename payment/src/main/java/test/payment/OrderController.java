package test.payment;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderRepository orderRepository;

  @GetMapping("/order")
  public ResponseEntity<UUID> createOrder() {
    final Order savedOrder = orderRepository.save(new Order(1000L, "호텔 주문"));
    log.info("주문 생성");
    return ResponseEntity.ok(savedOrder.getOrderId());
  }
}
