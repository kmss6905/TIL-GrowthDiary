package com.example.security;

import com.example.security.member.Member;
import com.example.security.member.MemberRepository;
import com.example.security.post.Post;
import com.example.security.post.PostController;
import com.example.security.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SecurityApplication implements CommandLineRunner {

  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(SecurityApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
    // save user
    Member member = Member.builder()
            .id(1L)
            .userId("user1")
            .password(passwordEncoder.encode("user1"))
            .build();

    Member member2 = Member.builder().userId("user2")
            .id(2L)
            .password(passwordEncoder.encode("user2"))
            .build();
    memberRepository.saveAll(List.of(member, member2));


    // save post
    postRepository.save(Post.builder()
            .content("User1의Post")
            .content("User1의Content")
            .memberId(1L)
            .build());
  }
}
