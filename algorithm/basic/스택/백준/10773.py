from collections import deque

a = deque()
for i in range(int(input())):
    n = int(input())
    if n == 0:
        a.pop()
    else:
        a.append(n)
print(sum(a))