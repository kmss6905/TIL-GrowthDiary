package com.example.security.domain.member;

import com.example.security.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findMemberByUserId(String userId);
}
