package com.example.concurrency.application;

import com.example.concurrency.domain.coupon.Coupon;
import com.example.concurrency.domain.coupon.CouponRepository;
import com.example.concurrency.domain.issue.IssuedCoupon;
import com.example.concurrency.domain.issue.IssuedCouponRepository;
import com.example.concurrency.dto.CouponIssueDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {

  private final CouponRepository couponRepository;
  private final IssuedCouponRepository issuedCouponRepository;

  public CouponService(CouponRepository couponRepository, IssuedCouponRepository issuedCouponRepository) {
    this.couponRepository = couponRepository;
    this.issuedCouponRepository = issuedCouponRepository;
  }

  @Transactional
  public void issue(CouponIssueDto couponIssueDto) {
    Coupon coupon = findCoupon(couponIssueDto.targetOwnerId());
    IssuedCoupon issuedCoupon = IssuedCoupon.from(couponIssueDto);
    issuedCouponRepository.save(issuedCoupon);
    coupon.increaseUse();
  }

  private Coupon findCoupon(long ownerId) {
    return couponRepository.findByIssuer(ownerId)
            .orElseThrow(IllegalArgumentException::new);
  }
}
