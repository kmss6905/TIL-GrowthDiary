package org.example.pagination;

import com.arakelian.faker.service.RandomPerson;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class PaginationApplication {

  private static final int POST_COUNT = 1000;
  private final PostRepository postRepository;

  @Bean
  ApplicationRunner startUp() {
    return args -> postRepository.saveAll(generatePosts());
  }

  public static void main(String[] args) {
    SpringApplication.run(PaginationApplication.class, args);
  }

  private List<Post> generatePosts() {
    List<Post> posts = new ArrayList<>();
    for (int i = 0; i < POST_COUNT; i++) {
      posts.add(new Post(
          RandomPerson.get().next().getFirstName(),
          RandomPerson.get().next().getBirthdate().toLocalDate()
      ));
    }
    return posts;
  }
}
