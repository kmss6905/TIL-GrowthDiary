# 완전 이진 트리 형태의 자료구조


* min heap: 부모 노드의 값이 자식 노드의 값보다 작은 트리 형태의 자료구조
* max heap: 부모 노드의 값이 자식 노드의 값보다 큰 트리 형태의 자료구조

형제 노트 간에는 대소 관계가 정해지지 않는다.
Root 노드가 가장 큰(or 작은) 값을 갖는다.

완전 이진 트리의 특성을 이용해서 굳이 노드가 아닌 리스트를 이용해서 구현할 수 있다.
(우선순위 큐를 사용하기 위함!)

```python3

import heapq
min_heap = [5, 3, 9, 4, 1, 2, 6]
heapq.heapify(min_heap) # min heap 구성
# [1, 3, 2, 4, 5, 9, 6]
heapq.heappop(min_heap)

# 마지막에 있는 노드를 맨 위 노드에 넣어준다. 
# 그 이후 자식 노드보다 작아야 하기 때문에 자식노드와 비교하여 자식 노드보다 우선순위가 높다면 스왑을 해준다(sift down)
# 우선 순위가 높을 때끼지 shft down 을 계속 한다.
# shft down 은 높이H(logN)만듬 이루어지기 때문에
# heapq.heappop(min_heap)  -> O(logN) 의 시간복잡도를 가진다.
heapq.heappop(min_heap) # dequeue
heapq.heappush(min_heap, 1) # enqueue, sift up 과정이 이루어 진다.


# heap 자료구조를 이용하여 우선순위큐를 구현한다면 Heap 을 이용해서 구현하는 것이 좋다.
# 데이터가 1000개 있을 경우 enqueue 와 , dequeue 모두 O(logN) 걸린다.


heapify () 의 경우 O(N) 걸림

```


### 정리
* heapify() -> O(N)
* heappush() -> O(logN)
* heappop() -> O(logN)