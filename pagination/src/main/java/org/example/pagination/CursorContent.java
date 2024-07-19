package org.example.pagination;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import org.springframework.data.domain.Slice;

@JsonInclude(Include.NON_NULL)
public record CursorContent<T>(
    List<T> contents,
    Long nextCursorId,
    boolean hasNext
) {

  public static <T> CursorContent<T> from(Slice<T> slices, Long nextCursorId) {
    return new CursorContent<>(slices.getContent(), nextCursorId, slices.hasNext());
  }

  public static <T> CursorContent<T> first(Slice<T> slices) {
    return new CursorContent<>(slices.getContent(), null, slices.hasNext());
  }
}
