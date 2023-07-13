import sys
input = sys.stdin.readline

def sol():
    n, k = map(int, input().split())
    cnt = 0
    # 증거1 : N의 값을 줄일 때 2이상의 수로 나누는 작업이 1을 빼는 작업보다 수를 훨씬 많이 줄일 수 있음
    # 정당성 분석
    # 가능하면 최대한 많이 나누는 작업이 최적의 해를 항상 보작할 수 있는가? YES
    # N이 아무리 큰 수여도, K로 계속 나눈다면 기하급수적으로 빠르게 줄일 수 있다.
    # 다시 말해 K가 2 이상이기만 하면, K로 나누는 것이 1을 뺴는 것보다 항상 빠르게 N을 줄일 수 있음
    # 또한 N은 항상 1에 도달함. (최적의 해 성립)
    while n != 1:
        # 나눌 수 있으면 최대한 많이 나누기
        if n % k == 0:
            n = n // k
        else:
            n -= 1
        cnt += 1
    return cnt

def sol2():
    n, k = map(int, input().split())
    result = 0
    while True:
        # n 이 나누어 떨어지지 않았을 때 가장 가까운 k를 나누어 떨어지는 수
        target = (n // k) * k

        # 1을 빼는 연산횟수를 한번에 넣어줌
        result += (n - target)
        n = target
        if n < k:
            break
        result += 1
        n //= k
    result += (n-1)
    print(result)

sol2()