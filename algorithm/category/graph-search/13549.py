from collections import deque

n, k = map(int, input().split())
road = [0] * 100001
q = deque()
q.append(n)
road[n] = 1
while q:
    cx = q.popleft()
    if cx == k:
        print(road[k] - 1)
        break
    for nx in (2*cx, cx-1, cx+1):
        if 0<= nx < 100001 and road[nx] == 0:
            if nx == 2*cx:
                road[nx] = road[cx] # road[10] = read[5] = 1
                q.appendleft(nx)
            else:
                road[nx] = road[cx] + 1
                q.append(nx)
