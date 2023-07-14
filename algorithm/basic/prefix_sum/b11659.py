# 구간합, prefix
import sys
input = sys.stdin.readline
n,m = map(int,input().split())
# index 위해 아무 의미 없는 값 넣기
a = [0] + list(map(int,input().split()))
s = [0]*(n+1)

# 합 배열
for i in range(1, n+1):
    s[i] = s[i-1] + a[i]
for _ in range(m):
    start, end = map(int,input().split())
    print(s[end]-s[start-1])