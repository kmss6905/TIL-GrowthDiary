from collections import deque
import sys
input = sys.stdin.readline

# # 시간 초과 남 -> 재풀이 필요
# def bfs(v):
#     q = deque([v])
#     visited = set()
#     visited.add(v)
#     while q:
#         next_n = q.popleft()
#         for i in graph[next_n]:
#             if i not in visited:
#                 q.append(i)
#                 visited.add(i)
#     return len(visited)


# n, m = map(int, input().split())
# graph = [[] for i in range(n+1)]
# for _ in range(m):
#     a, b = map(int, input().split())
#     graph[b].append(a)

# result = {}
# m = 0
# for i in range(1, n+1):
#     if len(graph[i]) != 0:
#         a = bfs(i)
#         m = max(a, m)
#         if a not in result:
#             result[a] = [i]
#         else:
#             result[a].append(i)
# print(' '.join(map(str, result[m])))


n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[b].append(a)
def bfs(k):
    cnt = 1
    visited = set()
    q = deque()
    q.append(k)
    while q:
        cur = q.popleft()
        for nx in graph[cur]:
            if nx not in visited:
                q.append(nx)
                visited
                cnt += 1
    return cnt

maxCnt = 1
ans = []
for i in range(1, n+1):
    cnt = bfs(i)
    if cnt > maxCnt:
        maxCnt = cnt
        ans.clear
        ans.append(i)
    elif cnt == maxCnt:
        ans.append(i)
print(*ans)