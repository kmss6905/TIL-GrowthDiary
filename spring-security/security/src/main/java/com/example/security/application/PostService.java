package com.example.security.application;

import com.example.security.domain.login.AuthenticatedMember;
import com.example.security.domain.post.Post;
import com.example.security.domain.post.PostRepository;
import com.example.security.presentation.post.PostEditRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public void editPost(long id, PostEditRequestDto postEditRequestDto) {
    Post post = findPost(id);

    // 도메인 객체가 presentation 에서 받는 요청객체에 직접적으로 의존하고 있다. 좋지는 않아보인다
    // 수정이 다소 빈번할 수 있는 요청 객체에 도메인이 직접적으로 의존하는 것에 대해서 어떻게 개선하면 좋을 지 생각하자.
    post.edit(postEditRequestDto);
  }
}
