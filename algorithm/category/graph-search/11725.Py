from collections import deque

n = int(input())
graph = [[] for _ in range(n+1)]
for _ in range(n-1):
    a, b = map(int, input().split())
    graph[a] += [b]
    graph[b] += [a]


p = {}
visited = []
q = deque([1])
visited.append(1)
while q:
    cur_n = q.popleft()
    for i in graph[cur_n]:
        if i not in visited:
            p[i] = cur_n
            visited.append(i)
            q.append(i)

for i in range(2, n+1):
    print(p[i])