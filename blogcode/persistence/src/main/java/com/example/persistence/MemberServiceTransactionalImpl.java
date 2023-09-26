package com.example.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceTransactionalImpl implements MemberService{

  private final MemberRepository memberRepository;

  public MemberServiceTransactionalImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Transactional
  @Override
  public MemberDto update(long id, String name) {
    Member member = memberRepository.findById(id).get();
    member.updateName(name);
    return MemberDto.of(member);
  }
}
