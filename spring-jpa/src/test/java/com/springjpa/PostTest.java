package com.springjpa;


import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostTest {

  private static final Logger logger = LoggerFactory.getLogger(PostTest.class);

  @Autowired
  PostRepository postRepository;

  @Autowired
  CommentRepository commentRepository;

  @PersistenceContext
  EntityManager em;

  @Test
  void nplusone() {
    // 3개의 post, 각 포스트 마다 10개의 댓글 보유
    Post postA = new Post("A");
    Post postB = new Post( "B");
    Post postC = new Post( "C");

    postA.setComments(generateRandomComments(10));
    postB.setComments(generateRandomComments(10));
    postC.setComments(generateRandomComments(10));

    postRepository.saveAll(List.of(postA, postB, postC));
    em.clear();

    System.out.println("--------------");
    // 1 번의 쿼리
    List<Post> posts = postRepository.findAll();

    // 엔티티의 개수 만큼 각 n 개의 쿼리가 추가로 발생
    // 전체 posts 조회하는 쿼리 1 -> posts 마다 추가로 comments 를 가져오기위한 쿼리 1 발생 따라서 총 posts 의 개수 n 만큼 쿼리 추가 발생
    // n + 1 쿼리
    assertThat(posts.isEmpty()).isFalse();
  }

  @Test
  void fetchJoin() {
    savePostAndComments();

    em.flush();
    em.clear();
    System.out.println("--------------");

    // 1 번의 쿼리
    List<Post> posts = postRepository.findAllJoinFetch();
    for (Post post : posts) {
      logger.info("post = {}", post);
    }
  }

  private void savePostAndComments() {
    Post postA = new Post("A");
    Post postB = new Post( "B");
    Post postC = new Post( "C");

    postA.setComments(generateRandomComments(10));
    postB.setComments(generateRandomComments(10));
    postC.setComments(generateRandomComments(10));

    postRepository.saveAll(List.of(postA, postB, postC));
  }

  @Test
  void anotherSolution() {
    savePostAndComments();
    em.flush();
    em.clear();

    System.out.println("----------------");
    List<Post> posts = postRepository.findAll();
    for (Post post : posts) {
      logger.info("post = {}", post);
    }
  }

  @Test
  void entityGraph() {
    savePostAndComments();
    em.flush();
    em.clear();

    System.out.println("----------------");
    List<Post> posts = postRepository.findAllPostsAndCommentsWithEntityGraph();
    for (Post post : posts) {
      logger.info("post = {}", post);
    }
  }

  public List<Comment> generateRandomComments(int cnt) {
    List<Comment> comments = new ArrayList<>();
    for (int i = 0; i < cnt; i++) {
      comments.add(new Comment(UUID.randomUUID().toString()));
    }
    return comments;
  }
}