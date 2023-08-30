package com.example.security.post;

import com.example.security.application.PostService;
import com.example.security.login.AuthenticatedMember;
import com.example.security.login.LoginUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @DeleteMapping("/{id}")
  public String deleteBook(
          @LoginUser AuthenticatedMember authenticatedMember,
          @PathVariable(name = "id") long bookId
  ) {
    postService.deleteBook(authenticatedMember, bookId);
    return "ok";
  }
}
