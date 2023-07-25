from collections import deque


def bfs(grid):
    m = len(grid)
    n = len(grid[0])
    visited = set()
    queue = deque([(0, 0, 1)])  # Start position and distance
    directions = [(-1, 0), (1, 0), (0, 1), (0, -1)]

    while queue:
        cuy, cux, c_cnt = queue.popleft()

        if (cuy, cux) == (m - 1, n - 1):
            return c_cnt

        for dy, dx in directions:
            next_x = cux + dx
            next_y = cuy + dy

            if 0 <= next_x < n and 0 <= next_y < m and grid[next_y][next_x] == '1' and (next_y, next_x) not in visited:
                visited.add((next_y, next_x))
                queue.append((next_y, next_x, c_cnt + 1))

    return 0


if __name__ == '__main__':
    m, n = map(int, input().split())
    grid = [list(input()) for _ in range(m)]
    print(bfs(grid))