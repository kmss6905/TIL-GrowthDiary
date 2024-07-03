package com.springjpa.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {


  private final PostService postService;

  public UserService(PostService postService) {
    this.postService = postService;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void createUserPost() {
    postService.createPost();
  }
}