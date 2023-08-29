package com.example.security.application;

import com.example.security.post.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void deleteBook(String id) {

  }
}
