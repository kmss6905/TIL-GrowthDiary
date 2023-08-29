package com.example.security;

import com.example.security.member.Member;
import com.example.security.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class SecurityApplication implements CommandLineRunner {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(SecurityApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
    String encode = passwordEncoder.encode("user");
    Member member = Member.builder().userId("user")
            .password(encode)
            .build();
    memberRepository.save(member);
  }
}
