package com.example.security.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Modifying
  @Query("delete from Post where id = :bookId")
  int deleteById(@Param("bookId") long bookId);
}
