from collections import deque

r, c, n = map(int, input().split())
graph = [list(input()) for i in range(r)]
visited = [['O'] * c for _ in range(r)]
directions = [
    (-1, 0), (1, 0), (0, 1), (0, -1)
]
def bfs(y, x):
    q = deque()
    q.append((y,x))
    visited[y][x] = '.'
    while q:
        cy, cx = q.popleft()
        for dy, dx in directions:
            ny = cy + dy
            nx = cx + dx
            if 0<= ny < r and 0 <= nx < c and graph[ny][nx] == ".":
                visited[ny][nx] = "."



if n % 2 == 0:
    for i in range(r):
        print('O' * c)
elif n % 4 == 1:
    for i in range(r):
        print(''.join(graph[i]))
elif n % 4 == 3:
    for i in range(r):
        for j in range(c):
            if graph[i][j] == 'O':
                bfs(i, j)
    for i in range(r):
        print(''.join(visited[i]))