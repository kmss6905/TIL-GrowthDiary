from collections import deque
queue = deque()
# enqueue() O(1) - rear 에 추가
queue.append(1)
queue.append(2)
queue.append(3)
queue.append(4)
# dequeue() O(1) - front 에서 빼감
queue.popleft()
queue.popleft()
queue.popleft()


# 원래 큐눈 뒤에 enqueue 앞에서 dequeue
# deque 는 front 에서 enqueue, rear 에서도 dequeue 할 수 있는 자료구조이다
# doubly ended queue = deque