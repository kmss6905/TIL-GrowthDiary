package com.example.concurrency;

import com.example.concurrency.domain.coupon.Coupon;
import com.example.concurrency.domain.coupon.CouponRepository;
import com.example.concurrency.domain.user.User;
import com.example.concurrency.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcurrencyApplication implements CommandLineRunner {

  private final CouponRepository couponRepository;
  private final UserRepository userRepository;

  public ConcurrencyApplication(CouponRepository couponRepository, UserRepository userRepository) {
    this.couponRepository = couponRepository;
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(ConcurrencyApplication.class, args);
  }

  @Override
  public void run(String... args){
    User user = User.builder()
            .id(1L)
            .name("user1")
            .build();

    Coupon coupon = Coupon.builder()
            .issuer(1L)
            .name("coupon")
            .build();

    userRepository.save(user);
    couponRepository.save(coupon);
  }
}
