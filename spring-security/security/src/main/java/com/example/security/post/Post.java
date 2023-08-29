package com.example.security.post;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.NONE)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String title;
  private String content;

  @Column(nullable = false)
  private long memberId;
}
