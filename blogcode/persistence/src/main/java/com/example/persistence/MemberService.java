package com.example.persistence;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }


  public MemberDto update(Long id, String name) {
    Member member = memberRepository.findById(id).get();
    member.updateName(name);
    return MemberDto.of(member);
  }

  @Transactional
  public MemberDto updateWithTransational(Long id, String name) {
    Member member = memberRepository.findById(id).get();
    member.updateName(name);
    return MemberDto.of(member);
  }
}
