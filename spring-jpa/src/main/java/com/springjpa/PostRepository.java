package com.springjpa;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("select p from Post p left join fetch p.comments")
  List<Post> findAllJoinFetch();

  // 외부 조인으로 동작한다.
  @EntityGraph(attributePaths = "comments")
  @Query("select p from Post p")
  List<Post> findAllPostsAndCommentsWithEntityGraph();
}
