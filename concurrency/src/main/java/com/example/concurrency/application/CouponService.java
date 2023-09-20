package com.example.concurrency.application;

import com.example.concurrency.domain.coupon.CouponRepository;
import com.example.concurrency.domain.issue.IssuedCouponRepository;
import com.example.concurrency.dto.CouponIssueDto;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

  private final CouponRepository couponRepository;
  private final IssuedCouponRepository issuedCouponRepository;

  public CouponService(CouponRepository couponRepository, IssuedCouponRepository issuedCouponRepository) {
    this.couponRepository = couponRepository;
    this.issuedCouponRepository = issuedCouponRepository;
  }

  public void issue(CouponIssueDto couponIssueDto) {

  }
}
