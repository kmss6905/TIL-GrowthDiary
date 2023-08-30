package com.example.security.post;


import com.example.security.exception.UnauthorizedAccessException;
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

  // check authorizatione
  public void checkIsAuthor(long memberId) {
    if (this.memberId != memberId) {
      throw new UnauthorizedAccessException("User is not authorized to access this post.");
    }
  }
}
