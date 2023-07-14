def sol(num):
    a = input().rstrip().split(' ')
    _map = {}
    for v in a:
        _map[v] = True
    b = int(input())  # 실제로 사용하지 않음 ..
    c = input().rstrip().split(' ')
    for v in c:
        if v in _map:
            print(1)
        else:
            print(0)


if __name__ == '__main__':
    sol(int(input()))
