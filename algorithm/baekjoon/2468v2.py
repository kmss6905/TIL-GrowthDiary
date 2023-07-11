from collections import deque
import sys

def sol():

    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    def bfs(y, x, rain):
        queue = deque()
        queue.append((y, x))
        visited[y][x] = True
        while queue:
            cy, cx = queue.popleft()
            for dy, dx in directions:
                next_x = cx + dx
                next_y = cy + dy
                if (
                    0 <= next_x < n
                    and 0 <= next_y < n
                    and region[next_y][next_x] > rain
                    and (visited[next_y][next_x] == False)
                ):
                    visited[next_y][next_x] = True
                    queue.append((next_y, next_x))


    n = int(sys.stdin.readline())
    region = []
    region_value = {0}
    for _ in range(n):
        row = list(map(int, sys.stdin.readline().split()))
        region_value.update(row)
        region.append(row)

    cnt = 0
    for rain in range(n):
        tmp = 0
        visited = [[False] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                if visited[i][j] == False and region[i][j] > rain:
                    tmp += 1
                    bfs(i, j, rain)
        cnt = max(cnt, tmp)
    return cnt


if __name__ == '__main__':
    print(sol())