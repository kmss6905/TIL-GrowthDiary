package com.example.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {

  @Id
  private Long id;
  private String name;

  public Member(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Member() {}

  public void updateName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name can't be null");
    }
    this.name = name;
  }
}
