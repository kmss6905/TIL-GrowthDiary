package com.example.security.application;

import com.example.security.login.AuthenticatedMember;
import com.example.security.post.Post;
import com.example.security.post.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void deleteBook(AuthenticatedMember authenticatedMember, long id) {
    Post post = findBook(id);
    post.checkIsAuthor(authenticatedMember.id());
    postRepository.delete(post);
  }

  private Post findBook(long id) {
    return postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("not fount book id : " + id));
  }
}
