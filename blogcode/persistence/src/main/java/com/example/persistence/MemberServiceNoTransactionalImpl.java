package com.example.persistence;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceNoTransactionalImpl implements MemberService {

  private final MemberRepository memberRepository;

  public MemberServiceNoTransactionalImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public MemberDto update(long id, String name) {
    Member member = memberRepository.findById(id).get();
    member.updateName(name);
    return MemberDto.of(member);
  }
}
