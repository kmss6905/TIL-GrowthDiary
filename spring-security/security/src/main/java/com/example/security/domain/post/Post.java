package com.example.security.domain.post;


import com.example.security.exception.UnauthorizedAccessException;
import com.example.security.presentation.post.PostEditRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
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

  // check authorization
  public void checkIsAuthor(long memberId) {
    if (this.memberId != memberId) {
      throw new UnauthorizedAccessException("User is not authorized to access this post.");
    }
  }

  public void edit(PostEditRequestDto postEditRequestDto) {
    changeTitle(postEditRequestDto.getTitle());
    changeContent(postEditRequestDto.getContent());
  }

  private void changeContent(String content) {
    if (content != null && !content.isEmpty()) {
      this.content = content;
    }
  }

  private void changeTitle(String title) {
    if (title != null && !title.isEmpty()) {
      this.title = title;
    }
  }
}
