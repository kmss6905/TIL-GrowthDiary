package com.springjpa.transaction;

import com.springjpa.Post;
import com.springjpa.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Transactional
  public void createPost() {
    postRepository.save(new Post("hello"));
    throw new RuntimeException();
  }
}