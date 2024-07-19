package org.example.pagination;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  public CursorContent<Post> findPage(Long cursorId, Pageable pageable) {
    final Slice<Post> posts = getPosts(cursorId, pageable);
    if (!posts.hasNext()) {
      new CursorContent<>(posts.getContent(), null, false, true);
    }

    final Long lastIdOfList = posts.isEmpty() ?
        null : posts.getContent().get(posts.getContent().size() - 1).getId();
    return CursorContent.from(posts, lastIdOfList);
  }

  private Slice<Post> getPosts(Long cursorId, Pageable pageable) {
    if (cursorId == null) {
      return postRepository.findAllBy(pageable);
    }

    return postRepository.findPostByIdLessThanOrderByIdDesc(cursorId, pageable);
  }

  public PageContent<Post> findPageWithOffset(Pageable pageable) {
    Slice<Post> posts = postRepository.findAllBy(pageable);
    return new PageContent<>(posts.getContent(), posts.hasNext());
  }
}
