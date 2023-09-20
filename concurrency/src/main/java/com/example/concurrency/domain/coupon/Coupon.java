package com.example.concurrency.domain.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
public class Coupon{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private long issuer;

  private long availableCoupons;
  private long maximumCoupons;

  @Builder
  public Coupon(final String name, final long issuer, final long availableCoupons, final long maximumCoupons) {
    this.name = name;
    this.issuer = issuer;
    this.availableCoupons = availableCoupons;
    this.maximumCoupons = maximumCoupons;
  }

  public void increaseUse() {
    verifyCanIssue();
    this.availableCoupons += 1;
  }
  private void verifyCanIssue() {
    if (this.availableCoupons >= this.maximumCoupons) {
      throw new IllegalArgumentException("남은 쿠폰이 없습니다.");
    }
  }

  public Coupon() {

  }
}