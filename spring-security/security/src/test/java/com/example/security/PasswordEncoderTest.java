package com.example.security;

import com.example.security.domain.member.Member;
import com.example.security.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PasswordEncoderTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void test() {
    Member member = memberRepository.findMemberByUserId("user").get();
    assertThat(passwordEncoder.matches("user", member.getPassword())).isTrue();
  }
}
