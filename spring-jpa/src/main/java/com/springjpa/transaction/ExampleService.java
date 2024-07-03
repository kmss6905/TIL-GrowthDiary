package com.springjpa.transaction;

import com.springjpa.Post;
import com.springjpa.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleService {

  private final PostRepository postRepository;

  public ExampleService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Transactional
  public void doService() {
    Post post = new Post("hello");
    postRepository.save(post);
  }
}
