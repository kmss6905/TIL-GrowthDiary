package com.example.persistence;


import org.springframework.stereotype.Service;

@Service
public class MemberServiceJpqlImpl implements MemberService {

  private final MemberRepository memberRepository;

  public MemberServiceJpqlImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public MemberDto update(long id, String name) {
    // jpql 을 이용한 update
    memberRepository.update(name, id);
    Member member = memberRepository.findMember(name).get();
    return MemberDto.of(member);
  }
}
