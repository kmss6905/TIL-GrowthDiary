package org.example.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort.Direction;

@Data
@AllArgsConstructor
public class SortParameter {
  private PostSearchType searchType;
  private Direction direction;
}
