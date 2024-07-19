package org.example.pagination;

import java.util.List;

public record PageContent<T>(
    List<T> posts,
    boolean hasNext
) {

}
