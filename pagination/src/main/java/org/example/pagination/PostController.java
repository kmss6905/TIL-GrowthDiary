package org.example.pagination;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;


  @GetMapping
  public ResponseEntity<CursorContent<Post>> page(
      @PageableDefault(sort = "id", direction = DESC) Pageable pageable,
      @RequestParam(required = false) Long nextCursor
  ) {
    var pageRequest = PageRequest.of(0, pageable.getPageSize());
    var cursorContent = postService.findPage(nextCursor, pageRequest);
    return ResponseEntity.ok(cursorContent);
  }

  @GetMapping("/offset")
  public ResponseEntity<PageContent<Post>> offsetPage(Pageable pageable) {
    return ResponseEntity.ok(postService.findPageWithOffset(pageable));
  }

  @GetMapping("/all")
  public ResponseEntity<List<Post>> posts() {
    return ResponseEntity.ok(postService.findAll());
  }

}
