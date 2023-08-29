package com.example.security.post;

import com.example.security.application.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @DeleteMapping("/{id}")
  public String deleteBook(@PathVariable String id) {
    postService.deleteBook(id);
    return "ok";
  }
}
