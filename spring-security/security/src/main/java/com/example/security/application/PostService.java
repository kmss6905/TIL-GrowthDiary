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

  public void deletePost(AuthenticatedMember authenticatedMember, long id) {
    Post post = findPost(id);
    post.checkIsAuthor(authenticatedMember.id());
    postRepository.delete(post);
  }

  public void deletePost(long bookId) {
    // 내부적으로 findById 실행 이후 삭제하기 때문에 쿼리가 두번 실행됩니다. 따라서 쿼리를 작성할 필요가 있음
    postRepository.deleteById(bookId);
  }

  private Post findPost(long id) {
    return postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("not fount book id : " + id));
  }
}
