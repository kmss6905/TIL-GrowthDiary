package com.springjpa;

import com.springjpa.transaction.PostService;
import com.springjpa.transaction.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringJpaApplication.class, args);
  }
}
