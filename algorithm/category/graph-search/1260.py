from collections import deque
n,m,s = map(int, input().split())
graph = [[] for i in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a] += [b]
    graph[b] += [a]


for i in graph:
    i.sort()


df_visited = []
def dfs(str_n):
    df_visited.append(str_n)
    for v in graph[str_n]:
        if v not in df_visited:
            dfs(v)

bf_visited = []
def bfs(str_n):
    q = deque([str_n])
    bf_visited.append(str_n)
    while q:
        cur = q.popleft()
        for v in graph[cur]:
            if v not in bf_visited: 
                q.append(v)
                bf_visited.append(v)


bfs(s)
dfs(s)
print(' '.join(map(str, df_visited)))
print(' '.join(map(str, bf_visited)))