package com.example.security.member;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String userId;
  private String password;

  @Builder
  public Member(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }

}
