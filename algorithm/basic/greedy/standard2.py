import sys
from collections import deque
input = sys.stdin.readline
def sol():
    s = input().strip()
    queue = deque()
    for i in list(s):
        w = int(i)
        if len(queue) == 0:
            queue.append(w)
        else:
            a = queue.popleft()
            if a == 0 or a == 1:
                queue.append(w+a)
            else:
                queue.append(w*a)
    return queue.popleft()

def sol2():
    data = input().strip()
    result = int(data[0]) # 이전 상태
    for i in range(1, len(data)):
        num = int(data[i])
        if num <= 1 or result <= 1: # 이전 것, 현재 것 둘 다 확인
            result += num
        else:
            result *= num
    return result
    

print(sol2())