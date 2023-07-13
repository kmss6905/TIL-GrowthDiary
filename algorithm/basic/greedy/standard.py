import sys
input = sys.stdin.readline

def sol():
    n, k = map(int, input().split())
    cnt = 0
    while n != 1:
        if n % k == 0:
            n = n // k
        else:
            n -= 1
        cnt += 1
    return cnt

print(sol())