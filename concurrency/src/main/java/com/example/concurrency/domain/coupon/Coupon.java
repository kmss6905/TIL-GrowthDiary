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

  @Builder
  public Coupon(final String name, final long issuer) {
    this.name = name;
    this.issuer = issuer;
  }

  public Coupon() {

  }
}