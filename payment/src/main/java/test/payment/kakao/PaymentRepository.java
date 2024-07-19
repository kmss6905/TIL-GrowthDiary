package test.payment.kakao;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.payment.kakao.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

  @Query(value = "select p from Payment p join p.order where p.order.orderId = :orderId")
  Optional<Payment> findByOrderId(UUID orderId);
}
