from collections import deque
n = int(input())
visited = [[0] * n for _ in range(n)]
graph = [list(input()) for _ in range(n)]
result = []
total = 0

def bfs(y, x):
    q = deque()
    q.append((y, x))
    visited[y][x] = 1
    cnt = 1
    directions = [(-1,0), (1,0),(0,1),(0,-1)]
    while q:
        cy, cx = q.popleft()
        for dy, dx in directions:
            ny = cy + dy
            nx = cx + dx
            if 0<= ny < n and 0<= nx < n and visited[ny][nx] == 0 and graph[ny][nx] == '1':
                visited[ny][nx] = 1
                q.append((ny,nx))
                cnt += 1
    return cnt


for i in range(n):
    for j in range(n):
        if graph[i][j] == '1' and visited[i][j] == 0:
            result.append(bfs(i, j))
            total += 1

print(total)
result.sort()
for i in result:
    print(i)
