def sol(num):
    map = {}
    for _ in range(num):
        s = input()
        if s[0] in map:
            map[s[0]] += 1
        else:
            map[s[0]] = 1

    s = []
    for i, v in map.items():
        if v >= 5:
            s.append(i)
    if len(s) == 0:
        return "PREDAJA"
    else:
        s.sort()
        return "".join(s)


if __name__ == '__main__':
    print(sol(int(input())))
