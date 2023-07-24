from collections import deque

n = int(input())
v = int(input())
graph = [[] for i in range(n + 1)]
visited = {}
for i in range(v):
    a, b = map(int, input().split())
    graph[a] += [b]
    graph[b] += [a]
visited[1] = True
queue = deque([1])
num = 0
while queue:
    c = queue.popleft()
    for nx in graph[c]:
        if nx not in visited:
            queue.append(nx)
            visited[nx] = True
            num += 1
print(num)