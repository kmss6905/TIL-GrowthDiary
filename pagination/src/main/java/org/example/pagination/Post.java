package org.example.pagination;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  Long id;

  @Column(name = "TITLE")
  String title;

  @Column(name = "CREATED_DATE")
  private LocalDate createdDate;

  public Post(String title, LocalDate createdDate) {
    this.title = title;
    this.createdDate = createdDate;
  }
}
