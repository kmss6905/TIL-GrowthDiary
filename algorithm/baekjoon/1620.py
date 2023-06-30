# O(N)
def sol():
    a, b = input().split(' ')
    table = {}
    # O(N)
    for i in range(1, int(a) + 1):
        name = input()
        table[str(i)] = name
        table[name] = str(i)

    # O(1)
    result = []
    for _ in range(int(b)):
        value = input()
        if value in table:
            result.append(table[value])
    for c in result:
        print(c)
if __name__ == '__main__':
    sol()
