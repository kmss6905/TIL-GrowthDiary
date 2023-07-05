from collections import deque

# leetcode 200, 섬의개수
class Solution(object):
  def numIslands(self, grid):
    number_of_islands = 0
    m = len(grid)
    n = len(grid[0])
    visited = [[False]*n for _ in range(m)] # vs visited[y,x]

    dx = [0, 0, -1, 1]
    dy = [-1, 1, 0, 0]

    def dfs(y, x):
      q = deque()
      q.append((y, x))
      visited[y][x] = True # visited
      while q:
        cur_y, cur_x = q.popleft()
        for l in range(4):
          next_x = cur_x + dx[l]
          next_y = cur_y + dy[l]
          if 0 <= next_x <= n - 1 and 0 <= next_y <= m - 1:
            if grid[next_y][next_x] == '1' and visited[next_y][next_x] is False:
              visited[next_y][next_x] = True
              q.append((next_y, next_x))

    # O(N^2) 최대 300 * 300 = 90000
    for i in range(m):
      for j in range(n):
        if grid[i][j] == '1' and visited[i][j] is False:  # O(1)
          dfs(i, j)
          number_of_islands += 1
    return number_of_islands


if __name__ == '__main__':
  grid = [
    ["1", "1", "0", "0", "0"],
    ["1", "1", "0", "0", "0"],
    ["0", "0", "1", "0", "0"],
    ["0", "0", "0", "1", "1"]
  ]
s = Solution()
print(s.numIslands(grid))
