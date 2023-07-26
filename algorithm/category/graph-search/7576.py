from collections import deque
import queue
m, n = map(int, input().split())
graph = [list(map(int,input().split())) for _ in range(n)]
visited = [[0]*m for _ in range(n)]
dy, dx = [-1, 1, 0, 0], [0, 0, -1, 1]
q = deque()
for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            visited[i][j] = 1
            q.append((i, j, 0))

def bfs():
    result = 0
    while q:
        cy, cx, cnt = q.popleft()
        for i in range(4):
            ny = cy + dy[i]
            nx = cx + dx[i]
            if 0 <= nx < m and 0 <= ny < n and graph[ny][nx] == 0:
                graph[ny][nx] = graph[cy][cx] + 1
                q.append((ny,nx, cnt+1))
                result = max(cnt+1, result)
    return result

result = bfs()
for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            print(-1)
            exit(0)
print(result)