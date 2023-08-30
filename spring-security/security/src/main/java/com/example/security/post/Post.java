package com.example.security.post;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String title;
  private String content;

  @Column(nullable = false)
  private long memberId;

  @Builder
  public Post(String title, String content, long memberId) {
    this.title = title;
    this.content = content;
    this.memberId = memberId;
  }
}
