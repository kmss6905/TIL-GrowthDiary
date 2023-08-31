package com.example.security.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PostRepositoryTest {

  @Autowired
  private PostRepository postRepository;

  @BeforeEach
  void test() {
    postRepository.save(
            Post.builder()
                    .content("1")
                    .title("1")
                    .memberId(1L).build());
  }

  @Test
  void te() {
    postRepository.deleteById(1L);
  }
}