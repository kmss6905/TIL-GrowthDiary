def sol(row):
    cnt = 0
    for _ in range(row):
        a = input()
        stack = []
        for v in a:
            if len(stack) == 0:
                stack.append(v)
            elif stack[-1] == v:
                stack.pop()
            else:
                stack.append(v)
        if len(stack) == 0:
            cnt += 1
    return cnt


if __name__ == '__main__':
    print(sol(int(input())))


