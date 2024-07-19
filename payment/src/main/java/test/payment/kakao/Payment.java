package test.payment.kakao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.payment.Order;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @Id
  private String tid;

  @Enumerated(value = EnumType.STRING)
  private PayStatus payStatus;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  public void changeToComplete() {
    this.payStatus = PayStatus.COMPLETED;
  }

  public void cancel() {
    this.payStatus = PayStatus.CANCEL;
  }

  public void fail() {
    this.payStatus = PayStatus.FAIL;
  }
}
