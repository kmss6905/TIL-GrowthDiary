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