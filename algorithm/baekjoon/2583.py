from collections import deque

def sol():
    m, n, c = map(int, input().split())
    region = [[0] * n for _ in range(m)]
    visited = [[False] * n for _ in range(m)]
    for _ in range(c):
        x1, y1, x2, y2 = map(int, input().split())
        for x in range(x1, x2):
            for y in range(y1, y2):
                region[y][x] = 1
    

    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]


    def bfs(y, x):
        queue = deque()
        queue.append((y, x))
        visited[y][x] = True
        s = 1
        while queue:
            cy, cx = queue.popleft()
            for dy, dx in directions:
                nx = cx + dx
                ny = cy + dy
                if 0<= nx < n and 0<= ny < m:
                    if region[ny][nx] == 0 and visited[ny][nx] is False:  # 갈 수 있으면서(0) 방문하지 않은 경우(False)
                        queue.append((ny, nx))
                        visited[ny][nx] = True
                        s += 1
        return s
                        
    result = []
    for i in range(m):
        for j in range(n):
            if visited[i][j] is False and region[i][j] == 0:
                result.append(bfs(i, j))

    result.sort()
    print(len(result))
    for v in result:
        print(v, end=" ")
    


if __name__ == '__main__':
    sol()