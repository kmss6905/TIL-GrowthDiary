from collections import deque

#
"""
전형적인 BFS 문제

아래와 같은 방법을 사용해서 시간 복잡도를 낮출 수 있음

1. 불필요한 데이터 구조 삭제: 현재 코드에서는 방문한 위치를 저장하기 위해 visited라는 세트를 사용하고 있습니다.
그러나 이 문제에서는 각 테스트 케이스마다 visited를 초기화하기 때문에, visited 데이터 구조를 사용하는 대신 방문한 위치를 직접 2차원 land 배열에 표시할 수 있습니다.
이렇게 하면 방문 여부를 확인하는 과정에서 세트에서의 검색을 생략할 수 있습니다.

2. 중복 방문 검사 최적화: 현재 코드에서는 큐에서 새로운 위치를 가져올 때마다 visited에 추가하는 작업을 수행하고 있습니다.
하지만 이 작업은 중복 방문을 체크하기 위한 것이므로, 큐에 새로운 위치를 추가할 때가 아닌 큐에 넣기 전에 중복 방문 여부를 확인하여 처리할 수 있습니다.
이렇게 하면 중복 방문을 큐에 추가하는 것을 방지하여 시간을 절약할 수 있습니다.

"""
def sol():
  ntc = input()
  result = []
  directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
  for _ in range(int(ntc)):
    cnt = 0
    visited = set()
    m, n, c = map(int, input().split(" "))
    land = [[0] * m for _ in range(n)]  # O(n^2)
    for _ in range(c):
      x, y = map(int, input().split(" "))
      land[y][x] = 1

    # O(N)
    def bfs(iy, ix):
      q = deque([[iy, ix]])
      while q:
        current_y, current_x = q.popleft()
        visited.add((current_y, current_x))  # O(1)
        for dy, dx in directions:
          next_x = current_x + dx
          next_y = current_y + dy
          if 0 <= next_y <= n - 1 and 0 <= next_x <= m - 1 and (next_y, next_x) not in visited and land[next_y][next_x] == 1:
            visited.add((next_y, next_x))  # O(1)
            q.append([next_y, next_x])

    # O(N^2)
    for i in range(len(land)):
      for j in range(len(land[0])):
        if (i, j) not in visited and land[i][j] == 1:
          bfs(i, j)  # O(N)
          cnt += 1
    result.append(cnt)
  return result





if __name__ == '__main__':
  result = sol()
  for i in result:
    print(i)
