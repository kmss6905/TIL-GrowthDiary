package com.example.security.presentation;

import com.example.security.application.PostService;
import com.example.security.domain.login.AuthenticatedMember;
import com.example.security.domain.login.LoginUser;
import com.example.security.interceptor.post.OwnerOnly;
import com.example.security.presentation.post.PostEditRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @DeleteMapping("/api/v1/posts/{id}")
  public String deleteBook(@LoginUser AuthenticatedMember authenticatedMember, @PathVariable long id) {
    postService.deletePost(authenticatedMember, id);
    return "ok";
  }

  @DeleteMapping("/api/v2/posts/{id}")
  @OwnerOnly
  public String deletePostVersion2(@PathVariable long id) {
    postService.deletePost(id);
    return "deleted ok";
  }

  @PatchMapping("/api/v2/posts/{id}")
  @OwnerOnly
  public String editPost(@PathVariable long id, @RequestBody PostEditRequestDto postEditRequestDto) {
    postService.editPost(id, postEditRequestDto);
    return "edit post ok";
  }
}