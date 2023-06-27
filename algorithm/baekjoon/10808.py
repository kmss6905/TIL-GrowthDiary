# O(N)
def sol(s):
    table = {}
    # O(1)
    for i in range(26):
        table[i] = 0
    # O(N) = O(N) + O(1)
    for v in s:
        asc = ord(v) - 97
        if asc in table:  # O(1)
            table[asc] += 1
    # O(N)
    for i in table.values():
        print(i, end=" ")


if __name__ == '__main__':
    sol(input())