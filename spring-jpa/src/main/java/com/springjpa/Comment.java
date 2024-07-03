package com.springjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  public String commentContent;

  @ManyToOne(optional = false)
  @JoinColumn(name = "post_id", nullable = false)
  public Post post;

  public Comment(String commentContent) {
    this.commentContent = commentContent;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", commentContent='" + commentContent;
  }
}
