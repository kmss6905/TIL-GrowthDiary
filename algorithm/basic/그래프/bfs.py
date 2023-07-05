from collections import deque


def bfs(graph, start_v):
  visited = [start_v]
  q = deque()
  q.append(start_v)
  while q:
    cv = q.popleft()
    for v in graph[cv]:
      if v not in visited:
        visited.append(v)
        q.append(v)
  return visited


if __name__ == '__main__':
  graph = {
    'A': ['B', 'E', 'D'],
    'B': ['A', 'C', 'D'],
    'C': ['B'],
    'D': ['A', 'B'],
    'E': ['A']
  }
  print(bfs(graph, 'A'))
