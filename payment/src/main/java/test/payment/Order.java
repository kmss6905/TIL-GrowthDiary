package test.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.payment.kakao.Payment;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ORDERS")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID orderId;
  private long price;
  private String name;

  @OneToMany(mappedBy = "order")
  private List<Payment> payment;

  public Order(long price, String name) {
    this.price = price;
    this.name = name;
  }
}
