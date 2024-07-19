http_examples 디렉토리 .http 파일 참고
### 1. CURSOR PAGINATION & SLICE (SCROLL) FIRST
GET http://localhost:8080/api/posts?size=10
Accept: application/json
```sql
select 
    p1_0.id,
    p1_0.created_date,
    p1_0.title
from post p1_0
    offset 0 rows fetch first 11 rows only
```
Slice 를 사용했기 때문에 size + 1 을 요청한다. 

### 2. CURSOR PAGINATION & SLICE (SCROLL)
GET http://localhost:8080/api/posts?size=10&nextCursor=991
Accept: application/json

```sql

select p1_0.id,
       p1_0.created_date,
       p1_0.title
from post p1_0 
where p1_0.id< CAST(991 AS BIGINT) order by p1_0.id desc offset 0 rows fetch first 11 rows only

```


### 3. CURSOR PAGINATION & SLICE & ORDER BY (SCROLL)
GET http://localhost:8080/api/posts?size=10&sort=createdDate,desc
Accept: application/json

### 4. OFFSET & SLICE (SCROLL)
GET http://localhost:8080/api/posts/offset?size=10&page=1&sort=createdDate,desc
Accept: application/json

```sql

select 
    p1_0.id,
    p1_0.created_date,
    p1_0.title 
from post p1_0 order by p1_0.created_date desc offset 10 rows fetch first 11 rows only

```


Repository Return 타입을 주의!!

`PageRequest.of(0, size)` OFFSET 0 이 들어간다. Pageable 을 만들기 위해서는 어쩔 수 없다.
실제 쿼리에서 OFFSET 예약어가 들어간다고 해도 0 이기 때문에 신경쓰지 않아도 된다.