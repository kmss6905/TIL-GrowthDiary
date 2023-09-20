package com.example.concurrency.domain.issue;

import com.example.concurrency.dto.CouponIssueDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
public class IssuedCoupon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long issuer;

  private long owner;

  @Builder
  public IssuedCoupon(final long issuer, final long owner) {
    this.issuer = issuer;
    this.owner = owner;
  }

  public IssuedCoupon() {

  }

  public static IssuedCoupon from(CouponIssueDto couponIssueDto) {
    return IssuedCoupon.builder()
            .issuer(couponIssueDto.targetOwnerId())
            .owner(couponIssueDto.fromUserId())
            .build();
  }
}
