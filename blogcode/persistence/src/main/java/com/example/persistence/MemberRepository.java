package com.example.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  @Query("select m from Member m where m.name =:name")
  Optional<Member> findMember(@Param("name") String name);

  @Modifying(clearAutomatically = true)
  @Query("update Member m SET m.name = :name where m.id = :id")
  void update(@Param("name") String name, @Param("id") long id);

}
