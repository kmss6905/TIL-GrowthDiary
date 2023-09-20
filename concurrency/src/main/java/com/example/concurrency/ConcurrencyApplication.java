package com.example.concurrency;

import com.example.concurrency.domain.coupon.Coupon;
import com.example.concurrency.domain.coupon.CouponRepository;
import com.example.concurrency.domain.user.User;
import com.example.concurrency.domain.user.UserRepository;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConcurrencyApplication implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(ConcurrencyApplication.class);

  private final CouponRepository couponRepository;
  private final UserRepository userRepository;

  private final DatabaseCleaner databaseCleaner;

  public ConcurrencyApplication(CouponRepository couponRepository, UserRepository userRepository, DatabaseCleaner databaseCleaner) {
    this.couponRepository = couponRepository;
    this.userRepository = userRepository;
    this.databaseCleaner = databaseCleaner;
  }

  public static void main(String[] args) {
    SpringApplication.run(ConcurrencyApplication.class, args);
  }

  @Override
  public void run(String... args){
    // clean database
    databaseCleaner.execute();


    // save default user, coupon
    User user = User.builder()
            .id(1L)
            .name("user1")
            .build();

    User user2 = User.builder()
            .id(2L)
            .name("user2")
            .build();

    Coupon coupon = Coupon.builder()
            .issuer(1L)
            .name("coupon")
            .availableCoupons(0L)
            .maximumCoupons(100L)
            .build();

    userRepository.save(user);
    userRepository.save(user2);
    couponRepository.save(coupon);
  }
}
