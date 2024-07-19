package org.example.pagination;

import java.util.List;
import org.springframework.data.domain.Slice;

public record CursorContent<T>(
    List<T> contents,
    Long nextCursorId,
    boolean hasNext,
    boolean isFirst
) {

  public static <T> CursorContent<T> from(Slice<T> slices, Long nextCursorId) {
    return new CursorContent<>(slices.getContent(), nextCursorId, slices.hasNext(), slices.isFirst());
  }
}
