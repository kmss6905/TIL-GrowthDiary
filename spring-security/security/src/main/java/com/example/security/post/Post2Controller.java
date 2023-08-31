package com.example.security.post;

import com.example.security.application.PostService;
import com.example.security.interceptor.AuthorAccessOnly;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class Post2Controller {

  private final PostService postService;

  public Post2Controller(PostService postService) {
    this.postService = postService;
  }

  @DeleteMapping("/{bookId}")
  @AuthorAccessOnly
  public String deleteBook(@PathVariable Long bookId) {
    postService.deletePost(bookId);
    return "delete ok";
  }
}
