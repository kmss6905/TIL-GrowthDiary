package com.example.security.config;

import com.example.security.argument.LoginArgumentResolver;
import com.example.security.interceptor.PostAuthorizationInterceptor;
import com.example.security.post.PostRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final PostRepository postRepository;

  public WebConfig(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginArgumentResolver());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new PostAuthorizationInterceptor(postRepository))
            .addPathPatterns("/api/books/*");
  }
}
