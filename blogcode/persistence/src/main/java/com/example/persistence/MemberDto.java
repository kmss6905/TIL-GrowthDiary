package com.example.persistence;

import lombok.Getter;

@Getter
public class MemberDto {
  private String name;

  public static MemberDto of(Member member) {
    MemberDto memberDto = new MemberDto();
    memberDto.name = member.getName();
    return memberDto;
  }
}
