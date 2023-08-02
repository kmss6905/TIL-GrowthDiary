from collections import deque
import sys

n = int(input())
graph = [[] for _ in range(n+1)]
for i in range(n-1):
    a, b = map(int, input().split())
    graph[a] += [b]
    graph[b] += [a]


q = deque()
q.append(1)
visited = set()
p = {}
while q:
    cur = q.popleft()
    for nx in graph[cur]:
        if nx not in visited:
            q.append(nx)
            visited.add(nx)
            p[nx] = cur

for i in range(2, n+1):
    print(p[i])