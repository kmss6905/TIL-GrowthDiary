import sys
num = int(input())
stack = []
answer = []
for i in range(num):
    # input() 을 사용하면 시간초과 난다.(idea)
    a = sys.stdin.readline().split()
    commend = a[0]
    if commend == "push":
        stack.append(a[1])
    elif commend == "pop":
        if not stack:
            print(-1)
        else:
            print(stack.pop())
    elif commend == "size":
        print(len(stack))
    elif commend == "empty":
        if stack:
            print(0)
        else:
            print(1)
    elif commend == "top":
        if not stack:
            print(-1)
        else:
            print(stack[-1])

        import sys

        input = sys.stdin.readline

# 다른 사람 풀이
def sol():
    stack = []
    N = int(input())

    for n in range(N):
        command = input().rstrip()  # idea 2 ( 공백제거 )
        if command == "pop":
            print(stack.pop() if stack else -1)  # pythonic
        elif command == "size":
            print(len(stack))
        elif command == "empty":
            print(+(not stack))
        elif command == "top":
            print(stack[-1] if stack else -1)
        else:
            _, x = command.split()  # idea 3
            stack.append(x)