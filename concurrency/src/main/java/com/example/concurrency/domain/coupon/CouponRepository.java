package com.example.concurrency.domain.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
