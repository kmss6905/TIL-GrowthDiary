package com.springjpa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  public String content;


  // Fech 사이즈,
//  @Fetch(FetchMode.SUBSELECT)
  @BatchSize(size = 10)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = CascadeType.PERSIST)
  public List<Comment> comments;

  public Post(String content) {
    this.content = content;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
    comments.forEach(it -> it.setPost(this));
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", comments=" + comments +
        '}';
  }
}
