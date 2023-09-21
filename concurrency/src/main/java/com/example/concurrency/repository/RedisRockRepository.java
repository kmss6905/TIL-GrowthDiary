package com.example.concurrency.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisRockRepository {
  private final RedisTemplate<String, String> redisTemplate;

  public RedisRockRepository(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public Boolean lock(Long key) {
    return redisTemplate
            .opsForValue()
            .setIfAbsent(generate(key), "lock", Duration.ofMillis(3_000));
  }

  public Boolean unlock(Long key) {
    return redisTemplate.delete(generate(key));
  }

  private String generate(Long key) {
    return key.toString();
  }
}
