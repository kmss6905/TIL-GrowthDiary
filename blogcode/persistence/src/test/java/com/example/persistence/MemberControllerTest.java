package com.example.persistence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private MemberRepository memberRepository;

  @BeforeEach
  void beforeEach() {
    memberRepository.deleteAll();
    memberRepository.save(new Member(100L, "user1"));
    memberRepository.flush();
  }

  @Test
  void shouldReturnNameUpdatedMember() throws Exception {
    mvc.perform(get("/members/{id}?name={name}", 100L, "user2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("user2"));


    Optional<Member> member = memberRepository.findMember("user2");
    Assertions.assertThat(member.isEmpty()).isFalse();
  }
}