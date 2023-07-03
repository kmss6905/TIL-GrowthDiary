def sol():
    a = input()
    size = len(a)
    table = {}
    for v in a:
        if v in table:
            table[v] += 1
        else:
            table[v] = 1

    if size % 2 == 0:
        l = []
        for i, v in table.items():
            if v % 2 != 0:
                return "I'm Sorry Hansoo"
            else:
                l.append(i*(int(v/2)))
        l.sort()
        front = "".join(l)
        l.reverse()
        back = "".join(l)
        return front + back

    else:
        cnt = 0
        center = ""
        l = []
        for i, v in table.items():
            if v % 2 == 1:
                cnt += 1
                center = i
                if v > 1:
                    l.append(i*int((v-1)/2))
            else:
                l.append(i*int(v/2))
        if cnt != 1:
            return "I'm Sorry Hansoo"
        l.sort()
        front = "".join(l)
        l.reverse()
        back = "".join(l)
        return front + center + back


if __name__ == '__main__':
    print(sol())
