import sys
input = sys.stdin.readline

def sol():
    n, k = list(map(int, input().split()))
    coins = []
    for _ in range(n):
        c = int(input())
        if c <= k:
            coins.append(c)
        
    cnt = 0
    for c in coins[::-1]:
        cnt += k // c
        k %= c
        if k == 0:
            return cnt

    return cnt

print(sol())
