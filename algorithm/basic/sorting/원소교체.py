n, k = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort() # nlogn
b.sort(reverse=True) # nlogn
print(sum(b[0:k]) + sum(a[k:]))