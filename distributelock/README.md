### Redis 를 활용한 분산락 구현

1. SET key value NX [EX second|PX milliseconds]
2. Redisson 활용 (pub/sub 방식)

Ru

### 자바 클라이언트

1. Jedis
2. Lettuce(`spring-boot-starter-data-redis` 가 사용하는 클라이언트)

`spring-boot-starter-data-redis` 의존성에는 `lettuce` 가 포함되어있음.(기존 lettuce 사용)

### Spring Data Redis
Spring 애플리케이션에서 Redis 에 대한 액세스를 제공한다.  
Redis 와 상호 작용하기 위한 낮은 수준 및 높은 수준의 추상화를 모두 제공한다.

* RedisTemplate : Redis Commands 를 추상화하여 사용하기 Spring 에서 사용하기 쉽게 만든 클래스. Redis 에게 Command 명령어를 보낸다. JdbcTemplate 과 같이 추상화하여 제공한다.


https://spring.io/projects/spring-data-redis
https://redis.io/learn/develop/java/redis-and-spring-course/lesson_2