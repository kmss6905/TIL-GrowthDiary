Repository Return 타입을 주의!!

`PageRequest.of(0, size)` OFFSET 0 이 들어간다. Pageable 을 만들기 위해서는 어쩔 수 없다.
실제 쿼리에서 OFFSET 예약어가 들어간다고 해도 0 이기 때문에 신경쓰지 않아도 된다.
따라서 쿼리에서 OFFSET 있다고 해도 당황하지 않아도 된다.

```java
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findPostByIdGreaterThan(long id, Pageable pageable);
  List<Post> findBy(Pageable pageable);
}
```
```SQL
select p1_0.id,p1_0.title from post p1_0 offset ? rows fetch first ? rows only
select p1_0.id,p1_0.title from post p1_0 where p1_0.id>? offset ? rows fetch first ? rows only                                              
```

```SQL


```