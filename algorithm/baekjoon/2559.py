import sys


# O(N)
def sol():
    input = sys.stdin.readline
    a, b = map(int, input().split())
    dd = list(map(int, input().split()))

    # O(1)
    current = sum(dd[:b])
    ll = [current]

    # O(N)
    for i in range(0, len(dd)-b):
        current = current - dd[i] + dd[i+b]
        ll.append(current)
    return max(ll)


if __name__ == '__main__':
    print(sol())
