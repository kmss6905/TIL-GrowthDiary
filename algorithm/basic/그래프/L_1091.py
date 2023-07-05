from collections import deque


class Solution(object):
  def shortestPathBinaryMatrix(self, grid):
    """
    :type grid: List[List[int]]
    :rtype: int
    """
    if grid is None:
      return -1

    n = len(grid)
    # dx = [0, 0, -1, 1, 1, 1, -1, -1]
    # dy = [-1, 1, 0, 0, -1, 1, -1, 1]
    directions = [(0, -1), (0, 1), (-1, 0), (1, 0), (1, -1), (1, 1), (-1, -1), (-1, 1)]
    q = deque()
    q.append((0, 0, 1))
    if grid[0][0] != 0 or grid[n - 1][n - 1] != 0:
      return -1
    visited = {}

    def dfs(i, j):
      m = -1
      while q:
        cur_y, cur_x, cnt = q.popleft()
        visited[(i, j)] = cnt
        for dx, dy in directions:
          next_y = cur_y + dy
          next_x = cur_x + dx
          if 0 <= next_x <= n - 1 and 0 <= next_y <= n - 1 and grid[next_y][next_x] == 0:
            if (next_y, next_x) not in visited:
              visited[(next_y, next_x)] = cnt + 1
              q.append((next_y, next_x, cnt + 1))
              if (next_y, next_x) in visited:
                m = visited[next_y, next_x]
      return m

    return dfs(0, 0)


if __name__ == '__main__':
  grid = [[0, 1], [1, 0]]
  s = Solution()
  print(s.shortestPathBinaryMatrix(grid))
