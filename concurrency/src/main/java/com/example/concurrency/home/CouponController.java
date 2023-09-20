package com.example.concurrency.home;

import com.example.concurrency.application.CouponService;
import com.example.concurrency.dto.CouponIssueDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

  private final CouponService couponService;

  public CouponController(CouponService couponService) {
    this.couponService = couponService;
  }

  @PostMapping("/issue")
  public ResponseEntity<String> couponIssue(
          @RequestBody CouponIssueDto couponIssueDto
  ) {
    couponService.issue(couponIssueDto);
    return ResponseEntity.ok("ok");
  }
}
