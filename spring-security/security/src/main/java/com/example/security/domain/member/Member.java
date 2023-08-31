package com.example.security.domain.member;

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
  public Member(long id,String userId, String password) {
    this.id = id;
    this.userId = userId;
    this.password = password;
  }

}
